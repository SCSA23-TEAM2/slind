package com.team2.slind.article.service;

import com.team2.slind.article.dto.request.ArticleCreateRequest;
import com.team2.slind.article.dto.response.ArticleCreateResponse;
import com.team2.slind.article.dto.response.ArticleMainResponse;
import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.vo.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleMapper articleMapper;
    private Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Transactional
    public ResponseEntity<ArticleCreateResponse> createArticle(ArticleCreateRequest articleCreateRequest, Long memberPk) {
        Long boardPk = articleCreateRequest.getBoard().getBoardPk();
        Article article = Article.builder().title(articleCreateRequest.getTitle()).
                articleContent(articleCreateRequest.getContent()).memberPk(memberPk)
                .articleBoard(Board.builder().boardPk(boardPk).build())
                .build();

        articleMapper.saveArticle(article);
        Long articlePk = articleMapper.findCreatedArticlePk();
        return ResponseEntity.ok().body(new ArticleCreateResponse(articlePk));
    }

    /**
     * @return
     *   메인 화면 최근 게시글에 띄울
     *   전체 게시판 중 가장 최근 게시글 10개 리스트 반환
     */
    public ResponseEntity<List<ArticleMainResponse>> findMainArticles() {
        List<Article> articleList = articleMapper.findRecentArticles();

        List<ArticleMainResponse> resultList = articleList.stream().map(article ->
            ArticleMainResponse.builder().articlePk(article.getArticlePk())
                    .boardPk(article.getArticleBoard().getBoardPk())
                    .boardTitle(article.getArticleBoard().getTitle())
                    .articleTitle(article.getTitle())
                    .viewCount(article.getViewCount())
                    .likeCount(article.getLikeCount())
                    .dislikeCount(article.getDislikeCount())
                    .commentCount(article.getComments().size())
                    .build()
        ).toList();
        return ResponseEntity.ok().body(resultList);
    }
}
