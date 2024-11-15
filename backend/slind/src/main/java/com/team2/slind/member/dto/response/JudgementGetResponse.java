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
public class JudgementGetResponse {
    private Long judgementPk;
    private Long articlePk;
    private Long boardPk;
    private String title;
    private LocalDateTime createdDttm;
}
