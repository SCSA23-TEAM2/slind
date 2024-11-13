package com.team2.slind.comment.mapper;

import com.team2.slind.comment.dto.response.CommentResponse;

import java.util.List;

public interface CommentMapper {
    List<CommentResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount);
}