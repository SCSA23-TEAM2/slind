package com.team2.slind.comment.mapper;

import com.team2.slind.comment.dto.response.CommentResponse;

import java.util.List;

public interface CommentMapper {
    List<CommentResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount);
    List<CommentResponse> getBestCommentList(Long articlePk, int fetchCount);
    List<CommentResponse> getRecommentList(Long originateComment, Long lastCommentPk, int fetchCount);
    Integer createComment(Long memberPk, Long articlePk, String commentContent);
    Integer updateComment(Long memberPk, Long commentPk, String commentContent);
    Integer deleteComment(Long memberPk, Long commentPk, String deleteMessage);
    Integer createRecomment(Long memberPk, Long originateComment, String commentContent);
}
