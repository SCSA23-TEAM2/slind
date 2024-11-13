package com.team2.slind.article.mapper;

import com.team2.slind.article.vo.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleMapper {
    Optional<Article> findByPk(Long pk);

    void saveArticle(Article article);
    Long findCreatedArticlePk();
    List<Article> findRecentArticles();
    void updateArticle(Article article);

    Long deleteArticle(Long articlePk);
}
