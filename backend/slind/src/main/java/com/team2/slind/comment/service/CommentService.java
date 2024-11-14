package com.team2.slind.comment.service;

import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.mapper.CommentReactionMapper;
import com.team2.slind.comment.vo.Comment;
import com.team2.slind.comment.vo.CommentReaction;
import com.team2.slind.common.exception.AlreadyDeletedException;
import com.team2.slind.common.exception.CommentNotFoundException;
import com.team2.slind.common.exception.ContentException;
import com.team2.slind.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentReactionMapper commentReactionMapper;
    private static final Long memberPk = 1L;

    public ResponseEntity<CommentListResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount) {
        CommentListResponse response = new CommentListResponse();
        List<CommentResponse> comments = commentMapper.getCommentList(articlePk, lastCommentPk, fetchCount);
        response.setHasNext(comments.size() > fetchCount);
        if (response.getHasNext()) {
            comments.remove(comments.size() - 1);
        }
        for (CommentResponse commentResponse : comments) {
            if (!commentResponse.getMemberPk().equals(memberPk)) {
                commentResponse.setIsMine(false);
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
                continue;
            }
            commentResponse.setIsMine(true);
            CommentReaction commentReaction = commentReactionMapper.findByCommentPkAndMemberPk(commentResponse.getCommentPk(), memberPk).orElse(null);
            if (commentReaction == null) {
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
            } else {
                commentResponse.setIsLike(commentReaction.getIsLike());
                commentResponse.setIsDislike(!commentReaction.getIsLike());
            }
        }
        response.setList(comments);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<CommentResponse>> getBestCommentList(Long articlePk, int fetchCount) {
        List<CommentResponse> comment = commentMapper.getBestCommentList(articlePk, fetchCount);
        for (CommentResponse commentResponse : comment) {
            if (!commentResponse.getMemberPk().equals(memberPk)) {
                commentResponse.setIsMine(false);
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
                continue;
            }
            commentResponse.setIsMine(true);
            CommentReaction commentReaction = commentReactionMapper.findByCommentPkAndMemberPk(commentResponse.getCommentPk(), memberPk).orElse(null);
            if (commentReaction == null) {
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
            } else {
                commentResponse.setIsLike(commentReaction.getIsLike());
                commentResponse.setIsDislike(!commentReaction.getIsLike());
            }
        }
        return ResponseEntity.ok(comment);
    }

    public ResponseEntity<CommentListResponse> getRecomment(Long commentPk, Long lastCommentPk, int fetchCount) {
        CommentListResponse response = new CommentListResponse();
        List<CommentResponse> comments = commentMapper.getRecommentList(commentPk, lastCommentPk, fetchCount);
        response.setHasNext(comments.size() > fetchCount);
        if (comments.size() > fetchCount) {
            comments.remove(comments.size() - 1);
        }
        response.setList(comments);
        for (CommentResponse commentResponse : comments) {
            if (!commentResponse.getMemberPk().equals(memberPk)) {
                commentResponse.setIsMine(false);
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
                continue;
            }
            commentResponse.setIsMine(true);
            CommentReaction commentReaction = commentReactionMapper.findByCommentPkAndMemberPk(commentResponse.getCommentPk(), memberPk).orElse(null);
            if (commentReaction == null) {
                commentResponse.setIsLike(false);
                commentResponse.setIsDislike(false);
            } else {
                commentResponse.setIsLike(commentReaction.getIsLike());
                commentResponse.setIsDislike(!commentReaction.getIsLike());
            }
        }
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<Void> createComment(Long memberPk, Long articlePk, String content) {
        long result = commentMapper.createComment(memberPk, articlePk, content);
        if (result == 0L) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> updateComment(Long memberPk, Long commentPk, String content) {
        Comment comment = commentMapper.findByPk(commentPk).orElseThrow(
                () -> new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND)
        );
        if (!comment.getMemberPk().equals(memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_UPDATE_COMMENT);
        } else if (comment.getIsDeleted().equals(1)) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_COMMENT);
        }
        long result = commentMapper.updateComment(memberPk, commentPk, content);
        if (result == 0L) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> deleteComment(Long memberPk, Long commentPk) {
        Comment comment = commentMapper.findByPk(commentPk).orElseThrow(
                () -> new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND)
        );
        if (!comment.getMemberPk().equals(memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_DELETE_COMMENT);
        } else if (comment.getIsDeleted().equals(1)) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_COMMENT);
        }
        long result = commentMapper.deleteComment(memberPk, commentPk, "삭제된 댓글입니다.");
        if (result == 0L) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> createRecomment(Long memberPk, Long commentPk, String content) {
        long result = commentMapper.createRecomment(memberPk, commentPk, content);
        if (result == 0L) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> updateRecomment(Long memberPk, Long commentPk, String content) {
        Comment comment = commentMapper.findByPk(commentPk).orElseThrow(
                () -> new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND)
        );
        if (!Objects.equals(comment.getMemberPk(), memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_UPDATE_COMMENT);
        } else if (content == null || content.trim().isEmpty()) {
            throw new ContentException(ContentException.EMPTY_CONTENT);
        } else if (content.length() > 1000) {
            throw new ContentException(ContentException.CONTENT_TOO_LONG);
        } else if (comment.getIsDeleted().equals(1)) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_COMMENT);
        }
        Long result = commentMapper.updateRecomment(memberPk, commentPk, content);
        if (result == 0L) {
            throw new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> deleteRecomment(Long memberPk, Long commentPk) {
        Comment comment = commentMapper.findByPk(commentPk).orElseThrow(
                () -> new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND)
        );
        if (!Objects.equals(comment.getMemberPk(), memberPk)) {
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_DELETE_COMMENT);
        } else if (comment.getIsDeleted().equals(1)) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_COMMENT);
        }
        Long result = commentMapper.deleteComment(memberPk, commentPk, "삭제된 댓글입니다.");
        if (result == 0L) {
            throw new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }
}
