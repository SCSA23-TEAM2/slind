package com.team2.slind.judgement.mapper;

import com.team2.slind.judgement.vo.Judgement;

public interface JudgementMapper {

    void saveJudgementArticle(Judgement articleJudgement);
    void saveJudgementBoard(Judgement boardJudgement);
    Long findCreatedJudgementPk();
}
