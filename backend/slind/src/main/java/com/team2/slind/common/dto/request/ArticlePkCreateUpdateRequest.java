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
public class ArticlePkCreateUpdateRequest {
    @NotNull
    Long articlePk;
    @NotNull @NotBlank
    @Size(min = 1, max = 200)
    String title;
    @NotNull @NotBlank
    @Size(min = 1, max = 2000)
    String content;



}
