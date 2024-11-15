package com.team2.slind.member.service;


import com.team2.slind.common.exception.AlreadyDeletedException;
import com.team2.slind.common.exception.DuplicateMemberIdException;
import com.team2.slind.common.exception.DuplicateNicknameException;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.request.MyPageUpdateRequest;
import com.team2.slind.member.dto.request.QuestionAnswerUpdateRequest;
import com.team2.slind.member.dto.response.MyPageInfoResponse;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.mapper.QuestionMapper;
import com.team2.slind.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final QuestionMapper questionMapper;
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
        int memberCount = memberMapper.findCountByPk(memberPk);
        if (memberCount == 0) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_MEMBER);
        }

        memberMapper.updateMemberInfo(myPageUpdateRequest.getNickname(), myPageUpdateRequest.getQuestionPk(),
                        myPageUpdateRequest.getAnswer(), memberPk);
        return ResponseEntity.ok().build();
    }

}
