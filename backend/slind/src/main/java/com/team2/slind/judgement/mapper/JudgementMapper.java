package com.team2.slind.judgement.mapper;

import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.member.dto.mapper.MyJudgementResponse;
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
    List<MyJudgementResponse> findListByMemberPk(@Param("memberPk") Long memberPk,
                                       @Param("lastPk") Long lastPk,
                                       @Param("size") int size);

    List<MyJudgementResponse> findListByMemberPkFirst(@Param("memberPk") Long memberPk,
                                                      @Param("size") int size);


    void finishJudgementWin(@Param("judgementPk") Long judgementPk);
    void finishJudgementLose(@Param("judgementPk") Long judgementPk);
    List<Judgement> findByStatus();

    List<Judgement> findList(@Param("offset") Integer offset,
                             @Param("judgementListSize") int judgementListSize);

    List<Judgement> findListOrderByViewCount(@Param("offset") Integer offset,
                                             @Param("judgementListSize") int judgementListSize);

    List<Judgement> findListOrderByLikeCount(@Param("offset") Integer offset,
                                             @Param("judgementListSize") int judgementListSize);

    Long findTotalRecords();
    Optional<Long> findPkByArticlePk(@Param("articlePk") Long articlePk);

}
