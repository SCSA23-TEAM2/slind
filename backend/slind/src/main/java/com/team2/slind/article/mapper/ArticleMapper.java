package com.team2.slind.article.mapper;

import com.team2.slind.article.vo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleMapper {
    Optional<Article> findByPk(Long pk);

    void saveArticle(Article article);

    Long findCreatedArticlePk();

    List<Article> findRecentArticles();

    void updateArticle(Article article);

    Long deleteArticle(@Param("articlePk") Long articlePk);

    Integer updateLikeCount(@Param("upCount") Integer upCount, @Param("articlePk") Long articlePk);

    Integer updateDislikeCount(@Param("upCount") Integer upCount, @Param("articlePk") Long articlePk);

    Integer findCountByPk(Long articlePk);
    Optional<Long> findMemberByPk(Long articlePk);
    Long findTotalRecords(Long boardPk);
    List<Article> findByBoardPk(Long boardPk, Integer offset, int articleListSize);
}
