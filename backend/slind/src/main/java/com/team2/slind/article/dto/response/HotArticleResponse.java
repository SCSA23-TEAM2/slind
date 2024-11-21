package com.team2.slind.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleResponse {
    private String boardTitle;
    private Long articlePk;
    private String articleTitle;
}
