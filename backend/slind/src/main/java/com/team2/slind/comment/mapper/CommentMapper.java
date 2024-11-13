package com.team2.slind.comment.mapper;

import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.vo.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentMapper {
    List<CommentResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount);
    List<CommentResponse> getBestCommentList(Long articlePk, int fetchCount);
    List<CommentResponse> getRecommentList(Long originateComment, Long lastCommentPk, int fetchCount);
    Long createComment(Long memberPk, Long articlePk, String commentContent);
    Long updateComment(Long memberPk, Long commentPk, String commentContent);
    Long deleteComment(Long memberPk, Long commentPk, String deleteMessage);
    Long createRecomment(Long memberPk, Long originateComment, String commentContent);
    Long updateRecomment(Long memberPk, Long commentPk, String commentContent);
    Optional<Comment> findByPk(Long commentPk);
}
