package com.team2.slind.comment.controller;

import com.team2.slind.comment.dto.request.CommentListRequest;
import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{articlePk}")
    public ResponseEntity<CommentListResponse> getCommentList(
            @PathVariable Long articlePk,
            @RequestParam(value = "lastCommentPk", required = true) Long lastCommentPk
            ) {
        return commentService.getCommentList(articlePk, lastCommentPk);
    }
}
