package com.team2.slind.judgement.controller;

import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.common.exception.InvalidRequestException;
import com.team2.slind.judgement.dto.request.JudgementReactionRequest;
import com.team2.slind.judgement.dto.response.JudgementDetailResponse;
import com.team2.slind.judgement.dto.response.JudgementListResponse;
import com.team2.slind.judgement.dto.response.JudgementPkResponse;
import com.team2.slind.judgement.service.JudgementService;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.security.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/judgement")
@RequiredArgsConstructor
public class JudgementController {

    private final JudgementService judgementService;


    @GetMapping("/{sort}/{page}")
    public ResponseEntity<JudgementListResponse> getJudgementList(
            @PathVariable("sort") Integer sort,
            @PathVariable("page") Integer page) {
        if (sort == null || sort < 0 || sort > 2 || page == null) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        }
        return judgementService.getJudgementList(sort, page);
    }

    @PostMapping("/auth/article")
    public ResponseEntity<JudgementPkResponse> createJudgementArticle(
            @RequestBody @Valid ArticlePkCreateUpdateRequest articlePkCreateUpdateRequest) {
        return judgementService.addJudgementArticle(articlePkCreateUpdateRequest, SecurityUtil.getMemberPk(true));
    }

    @PostMapping("/auth/board")
    public ResponseEntity<JudgementPkResponse> createJudgementBoard(
            @RequestBody @Valid BoardPkCreateUpdateRequest boardPkCreateUpdateRequest) {
        return judgementService.addJudgementBoard(boardPkCreateUpdateRequest, SecurityUtil.getMemberPk(true));
    }

    @GetMapping("/{judgementPk}")
    public ResponseEntity<JudgementDetailResponse> getJudgementDetail(
            @PathVariable("judgementPk") Long judgementPk) {
        return judgementService.getJudgementDetail(judgementPk, SecurityUtil.getMemberPk(false));

    }

    @PostMapping("/auth/reaction")
    public ResponseEntity<Void> createJudgementReaction(
            @RequestBody JudgementReactionRequest judgementReactionRequest){
        return judgementService.createJudgementReaction(judgementReactionRequest, SecurityUtil.getMemberPk(true));
    }
}
