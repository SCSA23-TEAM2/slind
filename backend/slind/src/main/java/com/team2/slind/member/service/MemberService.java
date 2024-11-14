package com.team2.slind.member.service;


import com.team2.slind.common.exception.DuplicateMemberIdException;
import com.team2.slind.common.exception.DuplicateNicknameException;
import com.team2.slind.common.exception.InvalidNicknameLengthException;
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
}
