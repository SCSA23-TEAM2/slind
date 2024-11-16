package com.team2.slind.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDetailResponse {
    private Long articlePk;
    private Long boardPk;
    private String title;
    private String articleContent;
    private String nickname;
    private int likeCount;
    private int dislikeCount;
    private int viewCnt;
    private int commentCount;
    private LocalDateTime createdDttm;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isJudgement;
    private Long judgementPk;
    private Boolean isMine;
}
