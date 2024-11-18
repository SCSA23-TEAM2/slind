package com.team2.slind.member.controller;

import com.team2.slind.common.exception.InvalidMemberIdLengthException;
import com.team2.slind.common.exception.InvalidNicknameLengthException;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.service.MemberService;
import com.team2.slind.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberSignupRequest memberSignupRequest) {
        return memberService.signup(memberSignupRequest);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        return memberService.logout();
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
}
