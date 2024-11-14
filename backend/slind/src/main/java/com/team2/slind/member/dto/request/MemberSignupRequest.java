package com.team2.slind.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupRequest {

    String memberId;

    String memberPassword;

    String nickname;

    Long questionPk;

    String answer;
}