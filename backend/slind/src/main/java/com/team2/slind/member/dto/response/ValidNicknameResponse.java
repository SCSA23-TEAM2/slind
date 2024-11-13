package com.team2.slind.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ValidNicknameResponse {
    private int status;
    private String message;

}
