package com.team2.slind.article.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReactionRequest {
    @NotNull
    private Long articlePk;
    @NotNull
    private Boolean isLike;
    @NotNull
    private Boolean isUp;
}
