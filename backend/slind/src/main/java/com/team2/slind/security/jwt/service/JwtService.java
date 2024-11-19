package com.team2.slind.security.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.slind.common.exception.InvalidAccessTokenException;
import com.team2.slind.common.exception.InvalidRefreshTokenException;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.vo.Member;
import com.team2.slind.security.jwt.domain.TokenRepository;
import com.team2.slind.security.jwt.dto.RefreshTokenDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String MEMBERID_CLAIM = "memberId";

    private static final String MEMBERPK_CLAIM = "memberPk";
    private static final String BEARER = "Bearer ";

    private final TokenRepository tokenRepository;
    private final RedisTemplate redisTemplate;
    private final MemberMapper memberMapper;

    public String createAccessToken(String memberId){

//        멤버 없을 때 exception 처리
//        if (member.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USER);
        Date now = new Date();
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
                .withClaim(MEMBERID_CLAIM, memberId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String createRefreshToken(String memberId){
        Date now = new Date();

        String refreshToken = JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));

        RefreshTokenDto refreshTokenDto = RefreshTokenDto.builder().
                memberId(memberId).
                refreshToken(refreshToken).build();
        tokenRepository.saveRefreshToken(refreshTokenDto);
        return refreshToken;
    }

    public void removeRefreshToken(String refreshToken) {

        Optional<String> memberId = extractMemberIdFromRefreshToken(refreshToken);
//        memberId 없을 때 exception 처리
//        if (memberId.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EMAIL_IN_REFRESH_TOKEN);
        tokenRepository.findByMemberId(memberId.get())

                .ifPresent(refreshTokenDto -> {
                    tokenRepository.deleteByMemberId(memberId.get());
                    log.info("Refresh Token 삭제 : {} ", refreshToken);


                });
    }

    public void sendAccessToken(HttpServletResponse response, String accessToken){
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("Access Token : {}", accessToken);
    }

    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken){
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        // JSON 객체를 만들기 위한 Map 생성
        tokens.put("refresh_token", refreshToken);

        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(tokens);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            log.info("Access Token, Refresh Token 리스폰스 바디에 설정 완료");
        } catch (IOException e) {
            log.error("토큰을 JSON으로 변환 중 오류 발생: {}", e.getMessage());
        }
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Optional<String> extractMemberIdFromAccessToken(String accessToken){
        Optional<String> memberId = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(accessToken.replace(BEARER, ""))
                .getClaim(MEMBERID_CLAIM).asString());

        // exception
//        if(memberId.isEmpty())
        return memberId;
    }
    public Optional<String> extractMemberIdFromRefreshToken(String refreshToken) {

        Optional<String> memberId = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(refreshToken.replace(BEARER, ""))
                .getClaim(MEMBERID_CLAIM).asString());

        // exception
//        if(email.isEmpty())


        return memberId;
    }
    public Optional<Long> extractMemberPkFromRefreshToken(String refreshToken) {
        Optional<Long> memberPk = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(refreshToken.replace(BEARER, ""))
                .getClaim(MEMBERPK_CLAIM).asLong());

        // exception
//        if(memberPk.isEmpty())

        return memberPk;
    }

    public void setAccessTokenHeader(HttpServletResponse response, String accessToken){
        response.setHeader(accessHeader, accessToken);
    }

    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken){
        response.setHeader(refreshHeader, refreshToken);
    }
    public Long getExpiration(String accessToken) {
        Optional<Date> expiration = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                .build().verify(accessToken.replace(BEARER, ""))
                .getExpiresAt());
//        if (expiration.isEmpty())
//            throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EXPIREAT_IN_ACCESS_TOKEN);
        Long now = new Date().getTime();
        return expiration.get().getTime() - now;

    }

    public boolean isAccessTokenValid(String accessToken){
        Optional<String> isLogoutOption = tokenRepository.findByKey(accessToken);
        if (isLogoutOption.isPresent())
            throw new InvalidAccessTokenException(InvalidAccessTokenException.INVALID_ACCESS_TOKEN);
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken);
            return true;
        } catch (Exception e) {
            throw new InvalidAccessTokenException(InvalidAccessTokenException.INVALID_ACCESS_TOKEN);
        }
    }

    public boolean isRefreshTokenValid(String refreshToken){
        log.info("isRefreshTokenValid 동작");
        Optional<String> memberId = extractMemberIdFromRefreshToken(refreshToken);
        log.info("refresh에서 추출한 memberId : {} " , memberId.get());
//        if (memberId.isEmpty()) throw new NoSuchElementException(NoSuchElementException.NO_SUCH_EMAIL_IN_REFRESH_TOKEN);
        Optional<RefreshTokenDto> savedRefreshTokenDto = tokenRepository.findByMemberId(memberId.get());


//        if (!savedRefreshTokenDto.isPresent()) {
//            throw new InvalidRefreshTokenException(InvalidRefreshTokenException.INVALID_REFRESH_TOKEN);
//        }

        // 여기서 JWT 라이브러리를 사용해 리프레시 토큰의 서명을 검증할 수 있습니다.
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(savedRefreshTokenDto.get().getRefreshToken());
        } catch (Exception e) {
            throw new InvalidRefreshTokenException(InvalidRefreshTokenException.INVALID_REFRESH_TOKEN);
        }

        return true;
    }

    public void saveBlackList(String accessToken) {

        if (isAccessTokenValid(accessToken)) {

            tokenRepository.saveBlackList(accessToken, getExpiration(accessToken));

        }
    }

}
