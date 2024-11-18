package com.team2.slind.comment.mapper;

import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.vo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface CommentMapper {
    List<CommentResponse> getCommentList(@Param("articlePk") Long articlePk,
                                         @Param("lastCommentPk") Long lastCommentPk,
                                         @Param("fetchCount") int fetchCount);

    List<CommentResponse> getBestCommentList(@Param("articlePk") Long articlePk,
                                             @Param("fetchCount") int fetchCount);

    List<CommentResponse> getRecommentList(@Param("originateComment") Long originateComment,
                                           @Param("lastCommentPk") Long lastCommentPk,
                                           @Param("fetchCount") int fetchCount);

    Long createComment(@Param("memberPk") Long memberPk,
                       @Param("articlePk") Long articlePk,
                       @Param("commentContent") String commentContent);

    Long updateComment(@Param("memberPk") Long memberPk,
                       @Param("commentPk") Long commentPk,
                       @Param("commentContent") String commentContent);

    Long deleteComment(@Param("memberPk") Long memberPk,
                       @Param("commentPk") Long commentPk,
                       @Param("deleteMessage") String deleteMessage);

    Long createRecomment(@Param("memberPk") Long memberPk,
                         @Param("originateComment") Long originateComment,
                         @Param("commentContent") String commentContent);

    Long updateRecomment(@Param("memberPk") Long memberPk,
                         @Param("commentPk") Long commentPk,
                         @Param("commentContent") String commentContent);

    Optional<Comment> findByPk(@Param("commentPk") Long commentPk);

    Integer updateLikeCount(@Param("upCount") Integer upCount,
                            @Param("commentPk") Long commentPk);

    Integer updateDislikeCount(@Param("upCount") Integer upCount,
                               @Param("commentPk") Long commentPk);

    List<Comment> findListByMemberPkFirst(@Param("memberPk") Long memberPk,
                                          @Param("size") int size);

    List<Comment> findListByMemberPk(@Param("memberPk") Long memberPk,
                                     @Param("lastCommentPk") Long lastCommentPk,
                                     @Param("size") int size);
}
