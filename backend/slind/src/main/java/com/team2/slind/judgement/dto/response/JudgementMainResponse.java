package com.team2.slind.judgement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgementMainResponse {
    Long judgementPk;
    String nickname;
    Boolean isArticle;
    String boardName;
    String articleTitle;
    String title;
    Integer viewCount;
    Integer likeCount;
    Integer dislikeCount;
}
