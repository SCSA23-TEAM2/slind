package com.team2.slind.judgement.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class JudgementReaction {
    private Long judgementReactionPk;
    private Long judgementPk;
    private Long memberPk;
}
