package com.team2.slind.judgement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgementDetailResponse {
    private Long judgementPk;
    private Long articlePk;
    private Long boardPk;
    private String title;
    private String content;
    private String nickname;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private LocalDateTime createdDttm;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isMine;
}
