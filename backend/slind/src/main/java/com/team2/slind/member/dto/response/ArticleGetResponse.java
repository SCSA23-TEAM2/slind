package com.team2.slind.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleGetResponse {
    private Long boardPk;
    private String boardTitle;
    private Long articlePk;
    private String articleTitle;
    private LocalDateTime createdDttm;
}
