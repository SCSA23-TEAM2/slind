package com.team2.slind.article.mapper;

import com.team2.slind.article.vo.ArticleReaction;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface ArticleReactionMapper {
    Optional<ArticleReaction> findByArticlePkAndMemberPk(@Param("articlePk") Long articlePk,
                                                         @Param("memberPk") Long memberPk);
    void deleteReactionByArticlePk(@Param("articleReactionPk") Long articleReactionPk);

    void updateReaction(@Param("isLike") Boolean isLike,
                        @Param("memberPk") Long memberPk, @Param("articlePk") Long articlePk);

    void saveReaction(ArticleReaction newReaction);
}
