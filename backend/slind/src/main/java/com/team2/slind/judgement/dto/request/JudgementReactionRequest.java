package com.team2.slind.judgement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgementReactionRequest {
    private Long judgementPk;
    private Boolean isLike;
}
