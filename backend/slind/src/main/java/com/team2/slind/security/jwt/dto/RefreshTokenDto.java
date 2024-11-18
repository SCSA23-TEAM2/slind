package com.team2.slind.security.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class RefreshTokenDto {

    private String memberId;

    private String refreshToken;
}
