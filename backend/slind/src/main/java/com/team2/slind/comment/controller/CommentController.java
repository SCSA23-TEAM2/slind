package com.team2.slind.comment.controller;

import com.team2.slind.comment.dto.request.CommentCreateRequest;
import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private static final Long memberPk = 1L;

    @GetMapping("/{articlePk}")
    public ResponseEntity<CommentListResponse> getCommentList(
            @PathVariable Long articlePk,
            @RequestParam(value = "lastCommentPk") Long lastCommentPk
            ) {
        return commentService.getCommentList(articlePk, lastCommentPk, 10);
    }

    @GetMapping("/{articlePk}/best")
    public ResponseEntity<List<CommentResponse>> getBestCommentList(
            @PathVariable Long articlePk
            ) {
        return commentService.getBestCommentList(articlePk, 3);
    }

    @GetMapping("/re/{commentPk}")
    public ResponseEntity<CommentListResponse> getComment(
            @PathVariable Long commentPk,
            @RequestParam(value = "lastCommentPk") Long lastCommentPk
            ) {
        return commentService.getRecomment(commentPk, lastCommentPk, 1);
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> createComment(
            @RequestBody CommentCreateRequest request
            ) {
        Long articlePk = request.getArticlePk();
        String content = request.getContent();
        return commentService.createComment(memberPk, articlePk, content);
    }
}
