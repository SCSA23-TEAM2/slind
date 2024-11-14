package com.team2.slind.judgement.mapper;

import com.team2.slind.judgement.vo.Judgement;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface JudgementMapper {

    void saveJudgementArticle(Judgement articleJudgement);
    void saveJudgementBoard(Judgement boardJudgement);
    Long findCreatedJudgementPk();

    Optional<Judgement> findJudgementByPk(@Param("judgementPk") Long judgementPk);

    void updateViewCount(@Param("judgementPk") Long judgementPk);
}
