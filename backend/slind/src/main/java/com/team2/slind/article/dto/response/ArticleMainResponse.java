package com.team2.slind.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleMainResponse {

    private Long articlePk;
    private Long boardPk;
    private String boardTitle;
    private String articleTitle;
    private Integer viewCount;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
}
