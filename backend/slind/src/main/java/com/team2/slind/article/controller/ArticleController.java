package com.team2.slind.article.controller;

import com.team2.slind.article.dto.request.ArticleCreateRequest;
import com.team2.slind.article.dto.response.ArticleCreateResponse;
import com.team2.slind.article.dto.response.ArticleMainResponse;
import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.service.ArticleService;
import com.team2.slind.article.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    static Long memberPk = 1L;

    @PostMapping("/auth")
    public ResponseEntity<ArticleCreateResponse> createArticle(@RequestBody ArticleCreateRequest articleCreateRequest) {
        return articleService.createArticle(articleCreateRequest, memberPk);

    }

    @GetMapping("/main")
    public ResponseEntity<List<ArticleMainResponse>> getMainArticles() {
        return articleService.findMainArticles();
    }


}
