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
import com.team2.slind.judgement.dto.response.JudgementListResponse;
import com.team2.slind.judgement.dto.response.JudgementMainResponse;
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

import java.util.ArrayList;
import java.util.List;
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
    private static final int JUDGEMENT_LIST_SIZE = 12;
    private static final int PAGE_SIZE = 10;
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

        String content = articlePkCreateUpdateRequest.getArticleContent();

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

        String content = requestDto.getArticleContent();

        Judgement articleJudgement =
                Judgement.builder().member(new Member(memberPk))
                        .boardPk(boardPk).title(title).judgementContent(content).build();
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
                        .status(judgement.getStatus())
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
        if (judgementReactionRequest.getIsLike()) {
            judgementMapper.updateLikeCount(judgementPk);
        } else {
            judgementMapper.updateDislikeCount(judgementPk);
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<JudgementListResponse> getJudgementList(Integer sort, Integer page) {
        List<Judgement> judgementList = null;
        Long totalRecords = judgementMapper.findTotalRecords();
        Long totalPage = totalRecords / JUDGEMENT_LIST_SIZE;
        if (totalRecords % JUDGEMENT_LIST_SIZE != 0) {
            totalPage++;
        }
        if (page < 1 || page > totalPage) {
            // return empty list
            return ResponseEntity.ok().body(JudgementListResponse.builder()
                    .list(new ArrayList<>())
                    .currentPage(page)
                    .totalPages(totalPage.intValue())
                    .startPage(1)
                    .endPage(1)
                    .hasPrevious(false)
                    .hasNext(false)
                    .build());
        }
        Integer startPage = (page - 1) / PAGE_SIZE * PAGE_SIZE + 1;
        Integer endPage = Math.min(startPage + PAGE_SIZE - 1, totalPage.intValue());
        Boolean hasPrevious = startPage > 1;
        Boolean hasNext = endPage < totalPage.intValue();
        Integer offset = (page - 1) * JUDGEMENT_LIST_SIZE;
        // print log
        log.info("sort:{}", sort);
        log.info("page:{}", page);
        log.info("totalRecords:{}", totalRecords);
        log.info("totalPage:{}", totalPage);
        log.info("startPage:{}", startPage);
        log.info("endPage:{}", endPage);
        log.info("hasPrevious:{}", hasPrevious);
        log.info("hasNext:{}", hasNext);
        log.info("offset:{}", offset);


        if (sort == 0) {
            judgementList = judgementMapper.findList(offset, JUDGEMENT_LIST_SIZE);
        } else if (sort == 1) {
            judgementList = judgementMapper.findListOrderByViewCount(offset, JUDGEMENT_LIST_SIZE);
        } else if (sort == 2) {
            judgementList = judgementMapper.findListOrderByLikeCount(offset, JUDGEMENT_LIST_SIZE);
        } else {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        }

        List<JudgementMainResponse> list = new ArrayList<>();
        for (Judgement judgement : judgementList) {
            String boardName = null;
            if (judgement.getBoardPk() != null) {
                //여기 이미 삭제된 게시판도 뜨게 하려면 다른 Mapper함수 만들어서 써야함
                Board board = boardMapper.findByBoardPkForJudgement(judgement.getBoardPk()).orElseThrow(() ->
                        new BoardNotFoundException(BoardNotFoundException.BOARD_NOT_FOUND));
                boardName = board.getTitle();
            }
            String articleTitle = null;
            if (judgement.getArticlePk() != null) {
                //여기 이미 삭제된 게시판도 뜨게 하려면 다른 Mapper함수 만들어서 써야함

                Article article = articleMapper.findByPkForJudgement(judgement.getArticlePk()).orElseThrow(() ->
                        new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND));
                articleTitle = article.getTitle();
            }
            list.add(JudgementMainResponse.builder()
                    .judgementPk(judgement.getJudgementPk())
                    .nickname(judgement.getMember().getNickname())
                    .isArticle(judgement.getArticlePk() != null)
                    .boardName(boardName)
                    .articleTitle(articleTitle)
                    .title(judgement.getTitle())
                    .viewCount(judgement.getViewCount())
                    .likeCount(judgement.getLikeCount())
                    .dislikeCount(judgement.getDislikeCount())
                    .build());
        }
        return ResponseEntity.ok().body(JudgementListResponse.builder()
                .list(list)
                .currentPage(page)
                .totalPages(totalPage.intValue())
                .startPage(startPage)
                .endPage(endPage)
                .hasPrevious(hasPrevious)
                .hasNext(hasNext)
                .build());
    }
}
