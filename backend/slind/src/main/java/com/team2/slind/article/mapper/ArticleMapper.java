package com.team2.slind.article.mapper;

import com.team2.slind.article.vo.Article;

public interface ArticleMapper {
    Article findByPk(Long pk);

    void saveArticle(Article article);
    Long findCreatedArticlePk();
}
