package com.team2.slind.judgement.dto.response;

import com.team2.slind.judgement.vo.Status;
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
    private String articleTitle;
    private Long boardPk;
    private String boardName;
    private String title;
    private String articleContent; // judgementContent (Not the content of the article)
    private String nickname;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private LocalDateTime createdDttm;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isMine;
    private Status status;
}
