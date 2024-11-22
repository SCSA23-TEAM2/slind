package com.team2.slind.member.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordFindRequest {
    @NonNull @Size(min = 4, max = 16)
    private String memberId;
    private Long questionPk;
    @NonNull @Size(max = 64)
    private String answer;
}