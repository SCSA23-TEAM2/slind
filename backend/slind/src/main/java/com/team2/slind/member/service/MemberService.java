package com.team2.slind.member.service;


import com.team2.slind.common.exception.DuplicateMemberIdException;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.response.ValidNicknameResponse;
import com.team2.slind.member.mapper.MemberMapper;
import com.team2.slind.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public ResponseEntity signupMember(MemberSignupRequest memberSignupRequest) {
        String memberId = memberSignupRequest.getMemberId();
        String nickname = memberSignupRequest.getNickname();
        String memberPassword = memberSignupRequest.getMemberPassword();
        Long questionPk = memberSignupRequest.getQuestionPk();
        String answer = memberSignupRequest.getAnswer();

        if (checkDuplicate(memberId)) {
            throw new DuplicateMemberIdException(
                    DuplicateMemberIdException.DUPLEICATE_MEMBERID
            );
        }

        Member member = Member.builder()
                .memberId(memberId)
                .nickname(nickname)
                .memberPassword(memberPassword)
                .questionPk(questionPk)
                .answer(answer).build();
        memberMapper.addMember(member);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity checkDuplicateMemberID(String memberId) {
        if (checkDuplicate(memberId)) {
            throw new DuplicateMemberIdException(DuplicateMemberIdException.DUPLEICATE_MEMBERID);
        }
        return ResponseEntity.ok().build();
    }

    public boolean checkDuplicate(String memberId) {
        Optional<Member> member = memberMapper.findByMemberId(memberId);
        return member.isPresent();
    }

//    public ValidNicknameResponse isValidNickname(String nickname) {
//        if (!checkNicknameLength(nickname)) {
//            return
//        }
//    }
//
//    private boolean checkNicknameLength(String nickname) {
//        return nickname != null && nickname.length() >= 1 && nickname.length() <= 16;
//    }
}
