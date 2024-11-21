package com.team2.slind.article.mapper;

import com.team2.slind.article.dto.mapper.ArticleDetailMapperDTO;
import com.team2.slind.article.dto.response.HotArticleResponse;
import com.team2.slind.article.vo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

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
    List<Article> findByBoardPk(@Param("boardPk") Long boardPk,
                                @Param("offset") Integer offset,
                                @Param("articleListSize") int articleListSize);
    List<Article> findAllByBoardPk(@Param("boardPk") Long boardPk);
    List<Article> findByBoardPkOrderByViewCount(@Param("boardPk") Long boardPk,
                                                @Param("offset") Integer offset,
                                                @Param("articleListSize") int articleListSize);
    List<Article> findByBoardPkOrderByLikeCount(@Param("boardPk") Long boardPk,
                                                @Param("offset") Integer offset,
                                                @Param("articleListSize") int articleListSize);

    List<Article> findListByMemberPkFirst(@Param("memberPk") Long memberPk, @Param("size") int size);

    List<Article> findListByMemberPk(@Param("memberPk") Long memberPk,
                                     @Param("lastPk") Long lastPk,
                                     @Param("size") int size);
    Optional<ArticleDetailMapperDTO> findArticleDetail(@Param("articlePk") Long articlePk);


    void updateViewCount(@Param("articlePk") Long articlePk);

    Optional<HotArticleResponse> findHotArticleResponses(@Param("articlePk") Long articlePk);

    Optional<Article> findByPkForJudgement(Long articlePk);
}
