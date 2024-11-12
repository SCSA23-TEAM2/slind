package com.team2.slind.comment.service;

import com.team2.slind.comment.dto.request.CommentListRequest;
import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.mapper.CommentReactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentReactionMapper commentReactionMapper;

    public ResponseEntity<CommentListResponse> getCommentList(Long articlePk, Long lastCommentPk) {
        CommentListResponse response = new CommentListResponse();
        return ResponseEntity.ok(response);
    }
}
