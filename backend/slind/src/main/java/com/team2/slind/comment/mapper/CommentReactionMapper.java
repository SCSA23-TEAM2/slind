package com.team2.slind.comment.mapper;

import com.team2.slind.comment.vo.CommentReaction;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Optional;

public interface CommentReactionMapper {
    Optional<CommentReaction> findByCommentPkAndMemberPk(@Param("commentPk") Long commentPk,
                                                         @Param("memberPk") Long memberPk);

    void updateReaction(@Param("memberPk") Long memberPk,
                        @Param("isLike") Boolean isLike,
                        @Param("commentPk") Long commentPk);

    void saveReaction(@Param("newReaction") CommentReaction newReaction);

    void deleteReactionByCommentPk(@Param("commentReactionPk") Long commentReactionPk);
}
