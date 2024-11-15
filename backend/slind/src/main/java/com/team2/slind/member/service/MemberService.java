package com.team2.slind.member.service;


import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.common.exception.AlreadyDeletedException;
import com.team2.slind.common.exception.DuplicateMemberIdException;
import com.team2.slind.common.exception.DuplicateNicknameException;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.request.MyPageUpdateRequest;
import com.team2.slind.member.dto.response.*;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.mapper.QuestionMapper;
import com.team2.slind.member.vo.Member;
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
public class MemberService {
    private final MemberMapper memberMapper;
    private final QuestionMapper questionMapper;
    private final JudgementMapper judgementMapper;
    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;


    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    final int size = 5;

    public ResponseEntity<Void> signup(MemberSignupRequest memberSignupRequest) {
        String memberId = memberSignupRequest.getMemberId();
        String nickname = memberSignupRequest.getNickname();

        if (isMemberIdDuplicated(memberId)) {
            throw new DuplicateMemberIdException(DuplicateMemberIdException.DUPLICATE_MEMBERID);
        }

        if (isNicknameDuplicated(nickname)) {
            throw new DuplicateNicknameException(DuplicateNicknameException.DUPLICATE_NICKNAME);
        }

        Member member = Member.builder()
                .memberId(memberId)
                .nickname(nickname)
                .memberPassword(memberSignupRequest.getMemberPassword())
                .questionPk(memberSignupRequest.getQuestionPk())
                .answer(memberSignupRequest.getAnswer())
                .build();
        memberMapper.addMember(member);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkDuplicateMemberId(String memberId){
        if (isMemberIdDuplicated(memberId)) {
            throw new DuplicateMemberIdException(DuplicateMemberIdException.DUPLICATE_MEMBERID);
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkDuplicateNickname(String nickname) {

        if (isNicknameDuplicated(nickname)) {
            throw new DuplicateNicknameException(DuplicateNicknameException.DUPLICATE_NICKNAME);
        }

        return ResponseEntity.ok().build();
    }


    private boolean isNicknameDuplicated(String nickname) {
        Optional<Member> member = memberMapper.findByNickname(nickname);
        return member.isPresent();
    }

    private boolean isMemberIdDuplicated(String memberId) {
        Optional<Member> member = memberMapper.findByMemberId(memberId);
        return member.isPresent();
    }

    public ResponseEntity<MyPageInfoResponse> getMyPageInfo(Long memberPk) {
        Member member = memberMapper.findByMemberPk(memberPk).orElse(null);
        if (member == null || member.getIsDeleted() == 1) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_MEMBER);
        }

        String question = questionMapper.findTextByPk(member.getQuestionPk());

        return ResponseEntity.ok().body(
                MyPageInfoResponse.builder().memberId(member.getMemberId())
                        .nickname(member.getNickname()).question(question)
                        .answer(member.getAnswer()).build());
    }

    @Transactional
    public ResponseEntity<Void> updateMypageInfo(Long memberPk, MyPageUpdateRequest myPageUpdateRequest) {

        memberMapper.updateMemberInfo(myPageUpdateRequest.getNickname(), myPageUpdateRequest.getQuestionPk(),
                        myPageUpdateRequest.getAnswer(), memberPk);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<InfiniteListResponse<JudgementGetResponse>> getMyJudgementList(
            Long memberPk, Long lastJudgementPk) {
        logger.info("Start : Get My Judgement List ");
        logger.info("memberPk : " + memberPk);
        logger.info("lastJudgementPk : " + lastJudgementPk);
        List<Judgement> list;


        if (lastJudgementPk == null){
            list = judgementMapper.findListByMemberPkFirst(memberPk, size);
        }else {
            list = judgementMapper.findListByMemberPk(memberPk, lastJudgementPk, size);
        }
        List<JudgementGetResponse> responseList = list.stream()
                .map(judgement -> JudgementGetResponse.builder().judgementPk(judgement.getJudgementPk())
                        .articlePk(judgement.getArticlePk())
                        .boardPk(judgement.getBoardPk()).title(judgement.getTitle())
                        .createdDttm(judgement.getCreatedDttm()).build())
                .toList();

        Boolean hasNext = list.size() > size;
        if (hasNext){
            responseList = responseList.subList(0, size);
        }
        logger.info("End : Get My Judgement List ");

        return ResponseEntity.ok().body(InfiniteListResponse.<JudgementGetResponse>builder().
                list(responseList).hasNext(hasNext).build());
    }

    public ResponseEntity<InfiniteListResponse<ArticleGetResponse>> getMyArticleList(Long memberPk, Long lastArticlePk) {
        logger.info("Start : Get My Article List ");
        logger.info("memberPk : " + memberPk);
        logger.info("lastArticlePk : " + lastArticlePk);

        List<Article> list;

        if (lastArticlePk == null) {
            list = articleMapper.findListByMemberPkFirst(memberPk, size);
        } else {
            list = articleMapper.findListByMemberPk(memberPk, lastArticlePk, size);
        }

        List<ArticleGetResponse> responseList = list.stream()
                .map(article -> ArticleGetResponse.builder()
                        .boardPk(article.getArticleBoard().getBoardPk())
                        .boardTitle(article.getArticleBoard().getTitle())
                        .articlePk(article.getArticlePk())
                        .articleTitle(article.getTitle())
                        .createdDttm(article.getCreatedDttm())
                        .build())
                .toList();

        Boolean hasNext = list.size() > size;
        if (hasNext) {
            responseList = responseList.subList(0, size);
        }

        logger.info("End : Get My Article List ");

        return ResponseEntity.ok().body(InfiniteListResponse.<ArticleGetResponse>builder()
                .list(responseList)
                .hasNext(hasNext)
                .build());
    }

    public ResponseEntity<InfiniteListResponse<BoardGetResponse>> getMyBoardList(Long memberPk, Long lastBoardPk) {
        logger.info("Start : Get My Board List ");
        logger.info("memberPk : " + memberPk);
        logger.info("lastBoardPk : " + lastBoardPk);

        List<Board> list;

        if (lastBoardPk == null) {
            list = boardMapper.findListByMemberPkFirst(memberPk, size);
        } else {
            list = boardMapper.findListByMemberPk(memberPk, lastBoardPk, size);
        }

        List<BoardGetResponse> responseList = list.stream()
                .map(board -> BoardGetResponse.builder()
                        .boardPk(board.getBoardPk())
                        .boardTitle(board.getTitle())
                        .createdDttm(board.getCreatedDttm())
                        .build())
                .toList();

        Boolean hasNext = list.size() > size;
        if (hasNext) {
            responseList = responseList.subList(0, size);
        }

        logger.info("End : Get My Board List ");

        return ResponseEntity.ok().body(InfiniteListResponse.<BoardGetResponse>builder()
                .list(responseList)
                .hasNext(hasNext)
                .build());
    }
}
