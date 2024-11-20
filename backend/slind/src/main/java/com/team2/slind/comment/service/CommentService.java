package com.team2.slind.comment.service;

import com.team2.slind.comment.dto.response.CommentListResponse;
import com.team2.slind.comment.dto.response.CommentResponse;
import com.team2.slind.comment.mapper.CommentMapper;
import com.team2.slind.comment.mapper.CommentReactionMapper;
import com.team2.slind.comment.vo.Comment;
import com.team2.slind.comment.vo.CommentReaction;
import com.team2.slind.common.exception.*;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentReactionMapper commentReactionMapper;

    public ResponseEntity<CommentListResponse> getCommentList(Long articlePk, Long lastCommentPk, int fetchCount) {
        Long memberPk = SecurityUtil.getMemberPk();
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
            Long commentPk = commentResponse.getCommentPk();
            CommentReaction commentReaction = commentReactionMapper.findByCommentPkAndMemberPk(commentPk, memberPk).orElse(null);
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
        Long memberPk = SecurityUtil.getMemberPk();
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
        Long memberPk = SecurityUtil.getMemberPk();
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

    @Transactional
    public ResponseEntity<Void> createReaction(Long memberPk, Long commentPk, Boolean isLike, Boolean isUp) {
        CommentReaction commentReaction = commentReactionMapper.findByCommentPkAndMemberPk(commentPk, memberPk).orElse(null);
        Comment comment = commentMapper.findByPk(commentPk).orElseThrow(
                () -> new CommentNotFoundException(CommentNotFoundException.COMMENT_NOT_FOUND)
        );
        if (comment.getIsDeleted().equals(1)) {
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_COMMENT);
        }
        if (isUp) {
            if (commentReaction != null) {
                if (commentReaction.getIsLike().equals(isLike)) {
                    throw new AlreadyReactedException(AlreadyReactedException.ALREADY_REACTED_COMMENT);
                } else {
                    // 기존 반응이 있고, requestIsLike와 isLike가 다르면 반응 바꾸기
                    commentReactionMapper.updateReaction(memberPk, isLike, commentPk);
                    // 게시글 count 변경 :  기존 반응 count -1
                    changeCommentReactionCount(!isLike, false, commentPk);
                }
            } else {
                // null이면 새로운 반응 save
                CommentReaction newReaction = CommentReaction.builder()
                        .commentPk(commentPk)
                        .memberPk(memberPk)
                        .isLike(isLike)
                        .build();
                commentReactionMapper.saveReaction(newReaction);
            }
            //새로운 반응 +1
            changeCommentReactionCount(isLike, true, commentPk);
        } else {
            //down 일때
            //null이면 nullException 반환 (취소할 반응이 없었던 것)
            if (commentReaction == null) {
                throw new NoReactionExcetpion(NoReactionExcetpion.NO_REACTION_FOR_DOWN_IN_COMMENT);
            }
            //requestIsLike와 isLike가 같지 않다면 Exception 반환
            if (isLike != commentReaction.getIsLike()) {
                throw new NoReactionExcetpion(NoReactionExcetpion.NO_REACTION_FOR_DOWN_IN_COMMENT);
            }
            commentReactionMapper.deleteReactionByCommentPk(commentReaction.getCommentReactionPk());
            changeCommentReactionCount(isLike, false, commentPk);
        }
        return ResponseEntity.ok().build();
    }

    protected void changeCommentReactionCount(Boolean isLike, Boolean isUp, Long commentPk) {
        Integer upCount = isUp ? 1 : -1;
        Integer result = null;
        if(isLike){
            result = commentMapper.updateLikeCount(upCount, commentPk);
        } else {
            result = commentMapper.updateDislikeCount(upCount, commentPk);
        }
        if (result.equals(0)){
            throw new FailedReactionException(FailedReactionException.FAILED_REACTION);
        }
    }
}
