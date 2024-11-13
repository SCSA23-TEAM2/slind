package com.team2.slind.comment.service;

import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.mapper.CommentReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentReactionMapper commentReactionMapper;

    public ResponseEntity<CommentListResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount) {
        CommentListResponse response = new CommentListResponse();
        List<CommentResponse> comments = commentMapper.getCommentList(articlePk, lastCommentPk, fetchCount);
        response.setHasNext(comments.size() > fetchCount);
        if (comments.size() > fetchCount) {
            comments.remove(comments.size() - 1);
        }
        response.setList(comments);
        // TODO: Implement user part
        for (CommentResponse commentResponse : comments) {
            commentResponse.setIsLike(false);
            commentResponse.setIsDislike(false);
            commentResponse.setIsMine(false);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<CommentResponse>> getBestCommentList(Long articlePk, int fetchCount) {
        List<CommentResponse> comment = commentMapper.getBestCommentList(articlePk, fetchCount);
        System.out.println(comment);
        for (CommentResponse commentResponse : comment) {
            commentResponse.setIsLike(false);
            commentResponse.setIsDislike(false);
            commentResponse.setIsMine(false);
        }
        // TODO: Implement user part
        return ResponseEntity.ok(comment);
    }

    public ResponseEntity<CommentListResponse> getRecomment(Long commentPk, Long lastCommentPk, int fetchCount) {
        CommentListResponse response = new CommentListResponse();
        List<CommentResponse> comments = commentMapper.getRecommentList(commentPk, lastCommentPk, fetchCount);
        response.setHasNext(comments.size() > fetchCount);
        if (comments.size() > fetchCount) {
            comments.remove(comments.size() - 1);
        }
        for (CommentResponse commentResponse : comments) {
            commentResponse.setIsLike(false);
            commentResponse.setIsDislike(false);
            commentResponse.setIsMine(false);
        }
        response.setList(comments);
        // TODO: Implement user part
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<Void> createComment(Long memberPk, Long articlePk, String content) {
        try {
            commentMapper.createComment(memberPk, articlePk, content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Void> updateComment(Long memberPk, Long commentPk, String content) {
        try {
            commentMapper.updateComment(memberPk, commentPk, content);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
