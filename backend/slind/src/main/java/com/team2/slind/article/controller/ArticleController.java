package com.team2.slind.article.controller;

import com.team2.slind.article.dto.response.ArticleDetailResponse;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.article.dto.request.ArticleReactionRequest;
import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.article.dto.response.ArticleListResponse;
import com.team2.slind.article.dto.response.ArticlePkResponse;
import com.team2.slind.article.dto.response.ArticleMainResponse;
import com.team2.slind.article.service.ArticleService;
import com.team2.slind.common.exception.InvalidRequestException;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.security.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping("/auth")
    public ResponseEntity<ArticlePkResponse> createArticle(@RequestBody BoardPkCreateUpdateRequest boardPkCreateUpdateRequest) {
        return articleService.createArticle(boardPkCreateUpdateRequest, SecurityUtil.getMemberPk(true));
    }

    @PutMapping("/auth")
    public ResponseEntity<ArticlePkResponse> updateArticle(@RequestBody ArticlePkCreateUpdateRequest articlePkCreateUpdateRequest) {
        return articleService.updateArticle(articlePkCreateUpdateRequest, SecurityUtil.getMemberPk(true));
    }

    @GetMapping("/detail/{articlePk}")
    public ResponseEntity<ArticleDetailResponse> getArticleDetail(@PathVariable("articlePk") Long articlePk) {
        Long memberPk = SecurityUtil.getMemberPk(false);
        return articleService.getArticleDetail(articlePk, memberPk);
    }

    @DeleteMapping("/auth/{articlePk}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articlePk") Long articlePk){
        return articleService.deleteArticle(articlePk, SecurityUtil.getMemberPk(true));
    }

    @GetMapping("/main")
    public ResponseEntity<List<ArticleMainResponse>> getMainArticles() {
        return articleService.findMainArticles();
    }

    @PostMapping("/auth/reaction")
    public ResponseEntity<Void> createReaction(@RequestBody @Valid ArticleReactionRequest articleReactionRequest) {
        return articleService.createReaction(articleReactionRequest, SecurityUtil.getMemberPk(true));
    }

    @GetMapping("/{boardPk}/{sort}/{page}")
    public ResponseEntity<ArticleListResponse> getArticles(
            @PathVariable("boardPk") Long boardPk,
            @PathVariable("sort") Integer sort,
            @PathVariable("page") Integer page) {
        if (sort == null || page == null || boardPk == null) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        } else if (sort < 0 || sort > 2) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        } else if (page < 0) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        }
        return articleService.getArticleList(boardPk, sort, page);
    }
}
