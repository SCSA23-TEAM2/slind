package com.team2.slind.judgement.controller;

import com.team2.slind.common.dto.request.ArticlePkCreateUpdateRequest;
import com.team2.slind.common.dto.request.BoardPkCreateUpdateRequest;
import com.team2.slind.judgement.dto.response.JudgementDetailResponse;
import com.team2.slind.judgement.dto.response.JudgementPkResponse;
import com.team2.slind.judgement.service.JudgementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/judgement")
@RequiredArgsConstructor
public class JudgementController {

    private final JudgementService judgementService;

    //JWT적용된 후 지우기
    static Long memberPk = 1L;

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
}
