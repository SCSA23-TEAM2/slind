package com.team2.slind.comment.controller;

import com.team2.slind.comment.dto.request.*;
import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.service.CommentService;
import com.team2.slind.common.exception.ContentException;
import com.team2.slind.common.exception.InvalidRequestException;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{articlePk}")
    public ResponseEntity<CommentListResponse> getCommentList(
            @PathVariable("articlePk") Long articlePk,
            @RequestParam(value = "lastCommentPk", required = false) Long lastCommentPk
            ) {
        if (lastCommentPk == null) {lastCommentPk=0L;}
        return commentService.getCommentList(articlePk, lastCommentPk, 10);
    }

    @GetMapping("/{articlePk}/best")
    public ResponseEntity<List<CommentResponse>> getBestCommentList(
            @PathVariable("articlePk") Long articlePk
            ) {
        return commentService.getBestCommentList(articlePk, 3);
    }

    @GetMapping("/re/{commentPk}")
    public ResponseEntity<CommentListResponse> getComment(
            @PathVariable Long commentPk,
            @RequestParam(value = "lastCommentPk") Long lastCommentPk
            ) {
        return commentService.getRecomment(commentPk, lastCommentPk, 3);
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> createComment(
            @RequestBody CommentCreateRequest request
            ) {
        Long articlePk = request.getArticlePk();
        String content = request.getContent();
        if (articlePk == null || content == null) {
            return ResponseEntity.badRequest().build();
        } else if (content.length() > 1000) {
            throw new ContentException(ContentException.CONTENT_TOO_LONG);
        } else if (content.isEmpty() || content.trim().isEmpty()) {
            throw new ContentException(ContentException.EMPTY_CONTENT);
        }
        return commentService.createComment(SecurityUtil.getMemberPk(true), articlePk, content);
    }

    @PostMapping("/auth/re")
    public ResponseEntity<Void> createRecomment(
            @RequestBody RecommentCreateRequest request
            ) {
        Long commentPk = request.getOriginateComment();
        String content = request.getContent();
        if (commentPk == null || content == null) {
            return ResponseEntity.badRequest().build();
        } else if (content.length() > 1000) {
            throw new ContentException(ContentException.CONTENT_TOO_LONG);
        } else if (content.isEmpty() || content.trim().isEmpty()) {
            throw new ContentException(ContentException.EMPTY_CONTENT);
        }
        return commentService.createRecomment(SecurityUtil.getMemberPk(true), commentPk, content);
    }

    @PutMapping("/auth")
    public ResponseEntity<Void> updateComment(
            @RequestBody CommentUpdateRequest request
            ) {
        Long commentPk = request.getCommentPk();
        String content = request.getContent();
        if (content == null || commentPk == null) {
            return ResponseEntity.badRequest().build();
        } else if (content.isEmpty() || content.trim().isEmpty()) {
            throw new ContentException(ContentException.EMPTY_CONTENT);
        } else if (content.length() > 1000) {
            throw new ContentException(ContentException.CONTENT_TOO_LONG);
        }
        return commentService.updateComment(SecurityUtil.getMemberPk(true), commentPk, content);
    }

    @PutMapping("/auth/re")
    public ResponseEntity<Void> updateRecomment(
            @RequestBody RecommentUpdateRequest request
            ) {
        Long commentPk = request.getCommentPk();
        String content = request.getContent();
        if (content == null || commentPk == null) {
            return ResponseEntity.badRequest().build();
        } else if (content.isEmpty() || content.trim().isEmpty()) {
            throw new ContentException(ContentException.EMPTY_CONTENT);
        } else if (content.length() > 1000) {
            throw new ContentException(ContentException.CONTENT_TOO_LONG);
        }
        return commentService.updateRecomment(SecurityUtil.getMemberPk(true), commentPk, content);
    }

    @DeleteMapping("/auth/{commentPk}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentPk
            ) {
        return commentService.deleteComment(SecurityUtil.getMemberPk(true), commentPk);
    }

    @DeleteMapping("/auth/re/{commentPk}")
    public ResponseEntity<Void> deleteRecomment(
            @PathVariable Long commentPk
            ) {
        return commentService.deleteRecomment(SecurityUtil.getMemberPk(true), commentPk);
    }

    @PostMapping("/auth/reaction")
    public ResponseEntity<Void> createReaction(
            @RequestBody CommentReactionRequest request
            ) {
        Long commentPk = request.getCommentPk();
        Boolean isLike = request.getIsLike();
        Boolean isUp = request.getIsUp();

        if (commentPk == null || isLike == null || isUp == null) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        }
        return commentService.createReaction(SecurityUtil.getMemberPk(true), commentPk, isLike, isUp);
    }
}
