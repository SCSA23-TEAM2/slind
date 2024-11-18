package com.team2.slind.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPkCreateUpdateRequest {
    @NotNull
    private Long boardPk;
    @NotNull @NotBlank
    @Size(min = 1, max = 200)
    private String title;
    @NotNull @NotBlank
    @Size(min = 1, max = 2000)
    private String articleContent;
}
