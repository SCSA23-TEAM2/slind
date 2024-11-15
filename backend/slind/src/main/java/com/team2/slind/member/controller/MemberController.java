package com.team2.slind.member.controller;

import com.team2.slind.common.exception.InvalidMemberIdLengthException;
import com.team2.slind.common.exception.InvalidNicknameLengthException;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.response.MyPageInfoResponse;
import com.team2.slind.member.dto.response.ValidNicknameResponse;
import com.team2.slind.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    static Long memberPk = 2L;
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberSignupRequest memberSignupRequest) {
        return memberService.signup(memberSignupRequest);
    }

    @GetMapping("/check-id")
    public ResponseEntity<Void> checkDuplicateMemberId(@RequestParam("memberId") String memberId) {
        if (memberId == null || memberId.length() < 4 || memberId.length() > 16) {
            throw new InvalidMemberIdLengthException(InvalidMemberIdLengthException.INVALID_MEMBERID_LENGTH);
        }
        return memberService.checkDuplicateMemberId(memberId);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Void> checkDuplicateNickname(@RequestParam("nickname") String nickname) {
        if (nickname == null || nickname.length() < 4 || nickname.length() > 16) {
            throw new InvalidNicknameLengthException(InvalidNicknameLengthException.INVALID_NICKNAME_LENGTH);
        }
        return memberService.checkDuplicateNickname(nickname);
    }


    @GetMapping("/auth/mypage")
    public ResponseEntity<MyPageInfoResponse> getMypageInfo(){
        return memberService.getMyPageInfo(memberPk);
    }
}
