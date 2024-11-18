package com.team2.slind.judgement.mapper;

import com.team2.slind.judgement.vo.Judgement;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JudgementMapper {

    void saveJudgementArticle(Judgement articleJudgement);
    void saveJudgementBoard(Judgement boardJudgement);
    Long findCreatedJudgementPk();

    Optional<Judgement> findJudgementByPk(@Param("judgementPk") Long judgementPk);

    void updateViewCount(@Param("judgementPk") Long judgementPk);
    int countByJudgementPk(@Param("judgementPk") Long judgementPk);
    List<Judgement> findListByMemberPk(@Param("memberPk") Long memberPk,
                                       @Param("lastPk") Long lastPk,
                                       @Param("size") int size);

    List<Judgement> findListByMemberPkFirst(@Param("memberPk") Long memberPk,
                                            @Param("size") int size);

    Optional<Long> findPkByArticlePk(@Param("articlePk") Long articlePk);

    void finishJudgementWin(@Param("judgementPk") Long judgementPk);
    void finishJudgementLose(@Param("judgementPk") Long judgementPk);
    List<Judgement> findByStatus();
}
