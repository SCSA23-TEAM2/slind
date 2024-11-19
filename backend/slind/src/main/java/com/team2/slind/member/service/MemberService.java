package com.team2.slind.member.service;


import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.vo.Comment;
import com.team2.slind.common.exception.*;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.member.dto.mapper.MyJudgementResponse;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.request.MyPageUpdateRequest;
import com.team2.slind.member.dto.response.*;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.mapper.QuestionMapper;
import com.team2.slind.member.vo.Member;
import com.team2.slind.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
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
        String encodedPassword = passwordEncoder.encode(memberSignupRequest.getMemberPassword());
        Member member = Member.builder()
                .memberId(memberId)
                .nickname(nickname)
                .memberPassword(encodedPassword)
                .questionPk(memberSignupRequest.getQuestionPk())
                .answer(memberSignupRequest.getAnswer())
                .build();
        memberMapper.addMember(member);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Void> logout() {
        // 현재 인증된 사용자의 토큰 정보를 SecurityContextHolder에서 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            String accessToken = (String) authentication.getCredentials();
            jwtService.saveBlackList(accessToken);
        } else {
            throw new IllegalStateException("인증된 토큰이 없습니다.");
        }
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

    public ResponseEntity<Void> deleteMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomMemberDetails memberDetails = (CustomMemberDetails) authentication.getPrincipal();
        Long memberPk = memberDetails.getMemberPk();

        Member member = memberMapper.findByMemberPk(memberPk).orElseThrow(()->
                new MemberNotFoundException(MemberNotFoundException.MEMBER_NOT_FOUND));

        memberMapper.deleteByMemberPk(memberPk);

        if (authentication.getCredentials() instanceof String) {
            String accessToken = (String) authentication.getCredentials();
            jwtService.saveBlackList(accessToken);
        }

        return ResponseEntity.ok().build();
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

    public ResponseEntity<InfiniteListResponse<MyJudgementResponse>> getMyJudgementList(
            Long memberPk, Long lastJudgementPk) {
        logger.info("Start : Get My Judgement List ");
        logger.info("memberPk : " + memberPk);
        logger.info("lastJudgementPk : " + lastJudgementPk);
        List<MyJudgementResponse> list;


        if (lastJudgementPk == null){
            list = judgementMapper.findListByMemberPkFirst(memberPk, size);
        }else {
            list = judgementMapper.findListByMemberPk(memberPk, lastJudgementPk, size);
        }
//        List<JudgementGetResponse> responseList = list.stream()
//                .map(judgement -> JudgementGetResponse.builder().judgementPk(judgement.getJudgementPk())
//                        .articlePk(judgement.getArticlePk())
//                        .boardPk(judgement.getBoardPk()).title(judgement.getTitle())
//                        .createdDttm(judgement.getCreatedDttm()).build())
//                .toList();

        Boolean hasNext = list.size() > size;
        if (hasNext){
            list = list.subList(0, size);
        }
        logger.info("End : Get My Judgement List ");

        return ResponseEntity.ok().body(InfiniteListResponse.<MyJudgementResponse>builder().
                list(list).hasNext(hasNext).build());
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

    public ResponseEntity<InfiniteListResponse<CommentGetResponse>> getMyCommentList
            (Long memberPk, Long lastCommentPk) {
        logger.info("Start : Get My Comment List");
        logger.info("memberPk : " + memberPk);
        logger.info("lastCommentPk : " + lastCommentPk);

        // 댓글 목록 가져오기
        List<Comment> comments;

        if (lastCommentPk == null) {
            comments = commentMapper.findListByMemberPkFirst(memberPk, size);
        } else {
            comments = commentMapper.findListByMemberPk(memberPk, lastCommentPk, size);
        }

        // 반환 DTO 생성
        List<CommentGetResponse> responseList = comments.stream()
                .map(comment -> {
                    logger.info("Comment : " , comment.getCommentPk());
                    logger.info("articlePk: {}" , comment.getArticlePk());
                    Article article = articleMapper.findByPk(comment.getArticlePk()).orElseThrow(()->
                            new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND));
                    return CommentGetResponse.builder()
                            .boardPk(article.getArticleBoard().getBoardPk())
                            .articlePk(article.getArticlePk())
                            .articleTitle(article.getTitle())
                            .commentPk(comment.getCommentPk())
                            .articleContent(comment.getCommentContent())
                            .createdDttm(comment.getCreatedDttm())
                            .build();
                })
                .toList();

        // hasNext 계산
        boolean hasNext = comments.size() > size;
        if (hasNext) {
            responseList = responseList.subList(0, size);
        }

        logger.info("End : Get My Comment List");

        return ResponseEntity.ok(InfiniteListResponse.<CommentGetResponse>builder()
                .list(responseList)
                .hasNext(hasNext)
                .build());

    }
}
