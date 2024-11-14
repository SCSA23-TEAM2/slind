package com.team2.slind.comment.mapper;

import com.team2.slind.comment.vo.CommentReaction;

import java.util.Optional;

public interface CommentReactionMapper {
    Optional<CommentReaction> findByCommentPkAndMemberPk(Long commentPk, Long memberPk);
}
