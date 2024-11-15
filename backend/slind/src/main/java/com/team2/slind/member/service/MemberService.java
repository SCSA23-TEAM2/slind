package com.team2.slind.member.service;


import com.team2.slind.common.exception.AlreadyDeletedException;
import com.team2.slind.common.exception.DuplicateMemberIdException;
import com.team2.slind.common.exception.DuplicateNicknameException;
import com.team2.slind.judgement.mapper.JudgementMapper;
import com.team2.slind.judgement.vo.Judgement;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.request.MyPageUpdateRequest;
import com.team2.slind.member.dto.response.JudgementGetInfiniteResponse;
import com.team2.slind.member.dto.response.JudgementGetResponse;
import com.team2.slind.member.dto.response.MyPageInfoResponse;
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
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    final int size = 10;

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

    public ResponseEntity<JudgementGetInfiniteResponse> getMyJudgementList(
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

        return ResponseEntity.ok().body(JudgementGetInfiniteResponse.builder().
                judgementList(responseList).hasNext(hasNext).build());
    }

}
