package com.team2.slind.judgement.service;

import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.common.exception.*;
import com.team2.slind.judgement.dto.request.JudgementReactionRequest;
import com.team2.slind.judgement.dto.response.JudgementDetailResponse;
import com.team2.slind.judgement.dto.response.JudgementPkResponse;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.mapper.JudgementReactionMapper;
import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.judgement.vo.JudgementReaction;
import com.team2.slind.member.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JudgementService {

    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;
    private final JudgementMapper judgementMapper;
    private final JudgementReactionMapper judgementReactionMapper;
    private final Logger logger = LoggerFactory.getLogger(JudgementService.class);
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
                Judgement.builder().member(new Member(memberPk)).articlePk(articlePk)
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
                Judgement.builder().member(new Member(memberPk))
                        .articlePk(boardPk).title(title).judgementContent(content).build();
        judgementMapper.saveJudgementBoard(articleJudgement);
        Long judgementPk = judgementMapper.findCreatedJudgementPk();
        return ResponseEntity.ok().body(new JudgementPkResponse(judgementPk));
    }

    @Transactional
    public ResponseEntity<JudgementDetailResponse> getJudgementDetail(Long judgementPk, Long memberPk) {
        Judgement judgement = judgementMapper.findJudgementByPk(judgementPk).orElseThrow(() ->
                new JudgementNotFoundException(JudgementNotFoundException.NOT_EXIST_JUDGEMENT));

        Optional<Boolean> reactionOpt = judgementReactionMapper.findByJudgementAndMember(judgementPk, memberPk);
        Boolean isLike = reactionOpt.orElse(false);;
        Boolean isDislike = !reactionOpt.orElse(true);;
        Boolean isMine = judgement.getMember().getMemberPk().equals(memberPk);

        Article articleOpt = null;
        String articleTitle = null;
        if (judgement.getArticlePk() != null) {
            articleOpt = articleMapper.findByPk(judgement.getArticlePk()).orElseThrow(() ->
                    new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND));
            articleTitle = articleOpt.getTitle();
        }

        Board boardOpt = null;
        String boardName = null;
        if (judgement.getBoardPk() != null) {
            boardOpt = boardMapper.findByBoardPk(judgement.getBoardPk()).orElseThrow(() ->
                    new BoardNotFoundException(BoardNotFoundException.BOARD_NOT_FOUND));
            boardName = boardOpt.getTitle();
        }

        JudgementDetailResponse response =
                JudgementDetailResponse.builder()
                        .judgementPk(judgementPk)
                        .boardPk(judgement.getBoardPk())
                        .boardName(boardName) // TODO
                        .articlePk(judgement.getArticlePk())
                        .articleTitle(articleTitle) // TODO
                        .title(judgement.getTitle())
                        .articleContent(judgement.getJudgementContent())
                        .nickname(judgement.getMember().getNickname())
                        .likeCount(judgement.getLikeCount())
                        .dislikeCount(judgement.getDislikeCount())
                        .viewCount(judgement.getViewCount())
                        .createdDttm(judgement.getCreatedDttm())
                        .isLike(isLike)
                        .isDislike(isDislike)
                        .isMine(isMine)
                        .build();
        judgementMapper.updateViewCount(judgementPk);
        return ResponseEntity.ok().body(response);
    }

    @Transactional
    public ResponseEntity<Void> createJudgementReaction(JudgementReactionRequest judgementReactionRequest, Long memberPk) {
        Long judgementPk = judgementReactionRequest.getJudgementPk();
        logger.info("judgementPk:{}", judgementPk);

        if(judgementMapper.countByJudgementPk(judgementPk)==0){
            throw new JudgementNotFoundException(JudgementNotFoundException.NOT_EXIST_JUDGEMENT);
        }

        if(judgementReactionMapper.countByJudgementAndMember(judgementPk, memberPk)!=0){
            throw new AlreadyReactedException(AlreadyReactedException.ALREADY_REACTED_JUDGEMENT);
        }
        JudgementReaction judgementReaction = JudgementReaction.builder().memberPk(memberPk)
                .judgementPk(judgementPk).isLike(judgementReactionRequest.getIsLike()).build();
        judgementReactionMapper.addJudgementReaction(judgementReaction);
        return ResponseEntity.ok().build();

    }
}
