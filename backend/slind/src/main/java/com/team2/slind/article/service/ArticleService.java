package com.team2.slind.article.service;

import com.team2.slind.article.dto.request.ArticleCreateRequest;
import com.team2.slind.article.dto.request.ArticleUpdateRequest;
import com.team2.slind.article.dto.response.ArticlePkResponse;
import com.team2.slind.article.dto.response.ArticleMainResponse;
import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.common.exception.ArticleNotFoundException;
import com.team2.slind.common.exception.ArticleNullException;
import com.team2.slind.common.exception.BoardNotFoundException;
import com.team2.slind.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;
    private Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Transactional
    public ResponseEntity<ArticlePkResponse> createArticle(ArticleCreateRequest articleCreateRequest, Long memberPk) {
        Long boardPk = articleCreateRequest.getBoardPk();
        boardMapper.findByBoardPk(boardPk).orElseThrow(()-> new BoardNotFoundException(BoardNotFoundException.BOARD_NOT_FOUND));
        String title = articleCreateRequest.getTitle();
        if (title == null || title.trim().isEmpty() ){
            throw new ArticleNullException(ArticleNullException.NULL_TITLE);
        }

        String articleContent = articleCreateRequest.getArticleContent();
        if (articleContent == null || articleContent.trim().isEmpty() ){
            throw new ArticleNullException(ArticleNullException.NULL_CONTENT);
        }

        Article article = Article.builder().title(title).
                articleContent(articleContent).memberPk(memberPk)
                .articleBoard(Board.builder().boardPk(boardPk).build())
                .build();

        articleMapper.saveArticle(article);
        Long articlePk = articleMapper.findCreatedArticlePk();
        return ResponseEntity.ok().body(new ArticlePkResponse(articlePk));
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


    @Transactional
    public ResponseEntity<ArticlePkResponse> updateArticle
            (ArticleUpdateRequest articleUpdateRequest, Long memberPk) {
        Long articlePk = articleUpdateRequest.getArticlePk();
        Article article = articleMapper.findByPk(articlePk).orElseThrow(()->
                new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND)
        );

        if (article.getMemberPk() != memberPk) {
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_UPDATE_ARTICE);
        }
        article.update(articleUpdateRequest.getTitle(), articleUpdateRequest.getArticleContent());
        articleMapper.updateArticle(article);
        return ResponseEntity.ok().body(new ArticlePkResponse(articlePk));
    }
}
