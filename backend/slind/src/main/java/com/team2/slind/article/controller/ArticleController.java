package com.team2.slind.article.controller;

import com.team2.slind.article.dto.request.ArticleCreateRequest;
import com.team2.slind.article.dto.response.ArticleCreateResponse;
import com.team2.slind.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    static Long memberPk = 1L;

    @PostMapping
    public ResponseEntity<ArticleCreateResponse> createArticle(@RequestBody ArticleCreateRequest articleCreateRequest) {
        return articleService.createArticle(articleCreateRequest, memberPk);

    }
}
