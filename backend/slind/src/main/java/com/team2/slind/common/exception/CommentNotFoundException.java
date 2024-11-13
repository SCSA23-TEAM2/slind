package com.team2.slind.common.exception;

public class CommentNotFoundException extends RuntimeException {
    public static final String COMMENT_NOT_FOUND = "댓글을 찾을 수 없습니다.";

    public CommentNotFoundException(String message) {
        super(message);
    }
}
