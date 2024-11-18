package com.team2.slind.security.jwt.filter;

import com.team2.slind.common.exception.InvalidTokenException;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.vo.Member;
import com.team2.slind.security.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
    private static final String NO_CHECK_URL = "/login";
    private final JwtService jwtService;
    private final MemberMapper memberMapper;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response); // "/login" 요청이 들어오면, 다음 필터 호출
            return; // return으로 이후 현재 필터 진행 막기 (안해주면 아래로 내려가서 계속 필터 진행시킴)
        }
        log.info("Processing Request : {}", request.getRequestURI());

        Optional<String> refreshToken = jwtService.extractRefreshToken(request);

        if (refreshToken.isPresent()) {
            checkRefreshTokenAndReIssueNewToken(response, refreshToken.get());
        } else {
            checkAccessToken(request, response, filterChain);
        }
    }

    public void checkRefreshTokenAndReIssueNewToken(HttpServletResponse response, String refreshToken) {
        try {
            if (jwtService.isRefreshTokenValid(refreshToken)) {
                String memberId = jwtService.extractMemberIdFromRefreshToken(refreshToken)
                        .orElseThrow(() -> new InvalidTokenException("Refresh Token에서 memberId를 추출할 수 없습니다."));
                jwtService.removeRefreshToken(refreshToken);

                // 새로운 토큰 생성
                String reIssuedRefreshToken = jwtService.createRefreshToken(memberId);

                String reIssuedAccessToken = jwtService.createAccessToken(memberId);

                // 새로운 토큰 전달
                jwtService.sendAccessAndRefreshToken(response, reIssuedAccessToken, reIssuedRefreshToken);
            }
        }catch (Exception e){

            log.error(e.getMessage());
        }
    }

    public void checkAccessToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtService.extractAccessToken(request).filter(jwtService::isAccessTokenValid)
                .ifPresent(accessToken -> jwtService.extractMemberIdFromAccessToken(accessToken).ifPresent(memberId -> {
                    log.info(memberId);
                    memberMapper.findByMemberId(memberId).ifPresent(member -> saveAuthentication(member, accessToken));
                }));
        filterChain.doFilter(request, response);
    }

    public void saveAuthentication(Member member, String accessToken) {
        CustomMemberDetails memberDetailsMember = CustomMemberDetails.builder()
                .memberPk(member.getMemberPk())
                .memberId(member.getMemberId())
                .memberPassword("null")
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(memberDetailsMember, accessToken, authoritiesMapper.mapAuthorities(memberDetailsMember.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
