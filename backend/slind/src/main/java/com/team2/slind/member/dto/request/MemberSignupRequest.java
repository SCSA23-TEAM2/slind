package com.team2.slind.member.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupRequest {

    @NonNull @Size(min = 4, max = 16)
    private String memberId;
    @NonNull @Size(min = 8, max = 20)
    private String memberPassword;
    @NonNull @Size(min = 1, max = 16)
    private String nickname;
    @NonNull
    private Long questionPk;
    @NonNull @Size(max = 64)
    private String answer;
}