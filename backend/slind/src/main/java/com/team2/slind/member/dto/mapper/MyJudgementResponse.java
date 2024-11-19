package com.team2.slind.member.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyJudgementResponse {
    private Long judgementPk;
    private Long articlePk;
    private String articleTitle;
    private Long boardPk;
    private String boardTitle;
    private String title;
    private LocalDateTime createdDttm;

}
