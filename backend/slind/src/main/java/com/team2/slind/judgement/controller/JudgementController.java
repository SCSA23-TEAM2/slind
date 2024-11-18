package com.team2.slind.judgement.controller;

import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.common.exception.InvalidRequestException;
import com.team2.slind.judgement.dto.request.JudgementReactionRequest;
import com.team2.slind.judgement.dto.response.JudgementDetailResponse;
import com.team2.slind.judgement.dto.response.JudgementListResponse;
import com.team2.slind.judgement.dto.response.JudgementPkResponse;
import com.team2.slind.judgement.service.JudgementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/judgement")
@RequiredArgsConstructor
public class JudgementController {

    private final JudgementService judgementService;

    //JWT적용된 후 지우기
    static Long memberPk = 1L;

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
        return judgementService.addJudgementArticle(articlePkCreateUpdateRequest, memberPk);
    }

    @PostMapping("/auth/board")
    public ResponseEntity<JudgementPkResponse> createJudgementBoard(
            @RequestBody @Valid BoardPkCreateUpdateRequest boardPkCreateUpdateRequest) {
        return judgementService.addJudgementBoard(boardPkCreateUpdateRequest, memberPk);
    }

    @GetMapping("/{judgementPk}")
    public ResponseEntity<JudgementDetailResponse> getJudgementDetail(
            @PathVariable("judgementPk") Long judgementPk) {
        return judgementService.getJudgementDetail(judgementPk, memberPk);

    }

    @PostMapping("/auth/reaction")
    public ResponseEntity<Void> createJudgementReaction(
            @RequestBody JudgementReactionRequest judgementReactionRequest){
        return judgementService.createJudgementReaction(judgementReactionRequest, memberPk);
    }
}
