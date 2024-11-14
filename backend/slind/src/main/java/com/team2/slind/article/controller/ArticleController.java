package com.team2.slind.article.controller;

import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.article.dto.request.ArticleReactionRequest;
import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.article.dto.request.ArticleUpdateRequest;
import com.team2.slind.article.dto.response.ArticleListResponse;
import com.team2.slind.article.dto.response.ArticlePkResponse;
import com.team2.slind.article.dto.response.ArticleMainResponse;
import com.team2.slind.article.service.ArticleService;
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
    public ResponseEntity<ArticlePkResponse> createArticle(@RequestBody BoardPkCreateUpdateRequest boardPkCreateUpdateRequest) {
        return articleService.createArticle(boardPkCreateUpdateRequest, memberPk);

    }

    @PutMapping("/auth")
    public ResponseEntity<ArticlePkResponse> updateArticle(@RequestBody ArticlePkCreateUpdateRequest articlePkCreateUpdateRequest) {
        return articleService.updateArticle(articlePkCreateUpdateRequest, memberPk);
    }

    @DeleteMapping("/auth/{articlePk}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articlePk") Long articlePk){
        return articleService.deleteArticle(articlePk, memberPk);
    }

    @GetMapping("/main")
    public ResponseEntity<List<ArticleMainResponse>> getMainArticles() {
        return articleService.findMainArticles();
    }

    @PostMapping("/auth/reaction")
    public ResponseEntity<Void> createReaction(@RequestBody ArticleReactionRequest articleReactionRequest) {
        return articleService.createReaction(articleReactionRequest, memberPk);
    }

    @GetMapping("/{boardPk}/{sort}/{page}")
    public ResponseEntity<ArticleListResponse> getArticles(
            @PathVariable("boardPk") Long boardPk,
            @PathVariable("sort") Integer sort,
            @PathVariable("page") Integer page) {
        if (sort == null || page == null || boardPk == null) {
            return ResponseEntity.badRequest().build();
        } else if (sort < 0 || sort > 2) {
            return ResponseEntity.badRequest().build();
        } else if (page < 0) {
            return ResponseEntity.badRequest().build();
        }
        return articleService.getArticleList(boardPk, sort, page);
    }
}
