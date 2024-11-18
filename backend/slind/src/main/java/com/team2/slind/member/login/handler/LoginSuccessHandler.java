package com.team2.slind.member.login.handler;

import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.security.jwt.domain.TokenRepository;
import com.team2.slind.security.jwt.dto.RefreshTokenDto;
import com.team2.slind.security.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final MemberMapper memberMapper;
    private final TokenRepository tokenRepository;
    @Value("${jwt.access.expiration}")
    private String accessTokenExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        String memberId = authentication.getName(); // 인증 정보에서 Username(memberId) 추출
        String accessToken = jwtService.createAccessToken(memberId); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
        String refreshToken = jwtService.createRefreshToken(memberId); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급
        
        // blackList에 동일 accessToken 삭제
        if (tokenRepository.findByKey(accessToken).isPresent()){
            tokenRepository.deleteByAccessToken(accessToken);
        }

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken); // 응답 헤더에 AccessToken, RefreshToken 실어서 응답

        memberMapper.findByMemberId(memberId)
                .ifPresent(memeber -> {
                    // Refresh Token을 DTO에 담기
                    RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder()
                            .memberId(memberId)
                            .refreshToken(refreshToken)
                            .build();

                    // Redis에 Refresh Token 저장
                    log.info(refreshTokenDto.toString());
                    log.info(memberId + " " + refreshToken);
                    tokenRepository.saveRefreshToken(refreshTokenDto);

                });
        CustomMemberDetails memberDetails = (CustomMemberDetails) authentication.getPrincipal();
        Long memberPk = memberDetails.getMemberPk(); // memberPk 가져오기
        log.info("memberPk : {}", memberPk);
        log.info("로그인에 성공하였습니다. 아이디 : {}", memberId);
        log.info("로그인에 성공하였습니다. AccessToken : {}", accessToken);
        log.info("로그인에 성공하였습니다. refreshToken : {}", refreshToken);
        log.info("발급된 AccessToken 만료 기간 : {}", accessTokenExpiration);
    }

}
