package com.team2.slind.member.controller;

import com.team2.slind.common.exception.InvalidMemberIdLengthException;
import com.team2.slind.common.exception.InvalidNicknameLengthException;
import com.team2.slind.member.dto.mapper.MyJudgementResponse;
import com.team2.slind.member.dto.request.MemberSignupRequest;
import com.team2.slind.member.dto.request.MyPageUpdateRequest;
import com.team2.slind.member.dto.response.*;
import com.team2.slind.member.service.MemberService;
import com.team2.slind.security.jwt.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    static Long memberPk = 1L;
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


    @GetMapping("/auth/mypage")
    public ResponseEntity<MyPageInfoResponse> getMypageInfo(){
        return memberService.getMyPageInfo(memberPk);
    }

    @PutMapping("/auth/mypage")
    public ResponseEntity<Void> updateMypageInfo(@RequestBody @Valid MyPageUpdateRequest myPageUpdateRequest){
        return memberService.updateMypageInfo(memberPk, myPageUpdateRequest);

    }

    @GetMapping({"/auth/judgement", "/auth/judgement/{lastJudgementPk}"})
    public ResponseEntity<InfiniteListResponse<MyJudgementResponse>> getMyJudgementList(
            @PathVariable(value = "lastJudgementPk", required = false) Long lastJudgementPk
    ){
        return memberService.getMyJudgementList(memberPk, lastJudgementPk);
    }

    @GetMapping({"/auth/article", "/auth/article/{lastArticlePk}"})
    public ResponseEntity<InfiniteListResponse<ArticleGetResponse>> getMyArticleList(
            @PathVariable(value = "lastArticlePk", required = false) Long lastArticlePk
    ){
        return memberService.getMyArticleList(memberPk, lastArticlePk);
    }

    @GetMapping({"/auth/board", "/auth/board/{lastBoardPk}"})
    public ResponseEntity<InfiniteListResponse<BoardGetResponse>> getMyBoardList(
            @PathVariable(value = "lastBoardPk", required = false) Long lastBoardPk
    ){
        return memberService.getMyBoardList(memberPk, lastBoardPk);
    }

    @GetMapping({"/auth/comment", "/auth/comment/{lastCommentPk}"})
    public ResponseEntity<InfiniteListResponse<CommentGetResponse>> getMyCommentList(
            @PathVariable(value = "lastCommentPk", required = false) Long lastCommentPk
    ){
        return memberService.getMyCommentList(memberPk, lastCommentPk);
    }

}
