package com.team2.slind.comment.service;

import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.mapper.CommentReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        response.setList(comments);
        // TODO: Implement user part
        for (CommentResponse commentResponse : comments) {
            commentResponse.setIsLike(false);
            commentResponse.setIsDislike(false);
            commentResponse.setIsMine(false);
        }
        return ResponseEntity.ok(response);
    }
}
