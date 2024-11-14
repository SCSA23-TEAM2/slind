package com.team2.slind.judgement.service;

import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.common.exception.ArticleNotFoundException;
import com.team2.slind.common.exception.BoardNotFoundException;
import com.team2.slind.common.exception.UnauthorizedException;
import com.team2.slind.judgement.dto.response.JudgementPkResponse;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.vo.Judgement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JudgementService {

    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;
    private final JudgementMapper judgementMapper;

    @Transactional
    public ResponseEntity<JudgementPkResponse> addJudgementArticle(
            ArticlePkCreateUpdateRequest articlePkCreateUpdateRequest, Long memberPk) {
        Long articlePk = articlePkCreateUpdateRequest.getArticlePk();
        Article article = articleMapper.findByPk(articlePk).orElseThrow(() -> new ArticleNotFoundException(
                ArticleNotFoundException.ARTICLE_NOT_FOUND
        ));

        if (article.getMemberPk().equals(memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.CANNOT_CREATE_JUDGEMENT_MY_ARTICLE);
        }
        String title = articlePkCreateUpdateRequest.getTitle();

        String content = articlePkCreateUpdateRequest.getContent();

        Judgement articleJudgement =
                Judgement.builder().memberPk(memberPk).articlePk(articlePk)
                        .title(title).judgementContent(content).build();
        judgementMapper.saveJudgementArticle(articleJudgement);
        Long judgementPk = judgementMapper.findCreatedJudgementPk();
        return ResponseEntity.ok().body(new JudgementPkResponse(judgementPk));
    }

    @Transactional
    public ResponseEntity<JudgementPkResponse> addJudgementBoard(
            BoardPkCreateUpdateRequest requestDto, Long memberPk) {
        Long boardPk = requestDto.getBoardPk();
        Board board = boardMapper.findByBoardPk(boardPk).orElseThrow(() -> new BoardNotFoundException(
                BoardNotFoundException.BOARD_NOT_FOUND
        ));

        if (board.getMemberPk().equals(memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.CANNOT_CREATE_JUDGEMENT_MY_ARTICLE);
        }
        String title = requestDto.getTitle();

        String content = requestDto.getContent();

        Judgement articleJudgement =
                Judgement.builder().memberPk(memberPk)
                        .articlePk(boardPk).title(title).judgementContent(content).build();
        judgementMapper.saveJudgementBoard(articleJudgement);
        Long judgementPk = judgementMapper.findCreatedJudgementPk();
        return ResponseEntity.ok().body(new JudgementPkResponse(judgementPk));
    }

}
