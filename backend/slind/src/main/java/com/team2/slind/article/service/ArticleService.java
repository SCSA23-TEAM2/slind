package com.team2.slind.article.service;

import com.team2.slind.article.dto.request.ArticleCreateRequest;
import com.team2.slind.article.dto.response.ArticleCreateResponse;
import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleMapper articleMapper;

    @Transactional
    public ResponseEntity<ArticleCreateResponse> createArticle(ArticleCreateRequest articleCreateRequest, Long memberPk) {
        Article article = Article.builder().title(articleCreateRequest.getTitle()).
                articleContent(articleCreateRequest.getContent()).memberPk(memberPk)
                .boardPk(articleCreateRequest.getBoardPk()).build();

        articleMapper.saveArticle(article);
        Long articlePk = articleMapper.findCreatedArticlePk();
        return ResponseEntity.ok().body(new ArticleCreateResponse(articlePk));
    }
}
