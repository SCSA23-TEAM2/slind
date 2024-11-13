package com.team2.slind.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequest {
    Long articlePk;
    String title;
    String articleContent;

}
