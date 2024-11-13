package com.team2.slind.article.mapper;

import com.team2.slind.article.vo.Article;

import java.util.List;

public interface ArticleMapper {
    Article findByPk(Long pk);

    void saveArticle(Article article);
    Long findCreatedArticlePk();
    List<Article> findRecentArticles();
}
