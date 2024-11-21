package com.team2.slind.member.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetRequest {
    private Long memberPk;
    @NonNull @Size(min = 8, max = 20)
    private String memberPassword;
}
