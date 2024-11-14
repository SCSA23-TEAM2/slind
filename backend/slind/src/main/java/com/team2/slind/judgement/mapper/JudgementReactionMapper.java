package com.team2.slind.judgement.mapper;

import com.team2.slind.judgement.vo.JudgementReaction;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface JudgementReactionMapper {
    Optional<Boolean> findByJudgementAndMemebr(@Param("judgementPk")Long judgementPk,
                                               @Param("memberPk") Long memberPk);
}
