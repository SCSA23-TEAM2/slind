package com.team2.slind.common.exception;

public class UnauthorizedException extends RuntimeException {
    public static final String UNAUTHORIZED_DELETE_BOARD = "게시판 삭제 권한이 없는 계정입니다.";
    public static final String UNAUTHORIZED_UPDATE_ARTICLE = "게시글 수정 권한이 없는 계정입니다.";
    public static final String UNAUTHORIZED_DELETE_ARTICLE = "게시글 삭제 권한이 없는 계정입니다.";
    public static final String UNAUTHORIZED_UPDATE_COMMENT = "댓글 수정 권한이 없는 계정입니다.";
    public static final String UNAUTHORIZED_DELETE_COMMENT = "댓글 삭제 권한이 없는 계정입니다.";
    public static final String CANNOT_CREATE_JUDGEMENT_MY_ARTICLE = "자신의 게시글을 재판할 수 없습니다.";
    public static final String CANNOT_CREATE_JUDGEMENT_MY_BOARD = "자신의 게시판을 재판할 수 없습니다.";

    public UnauthorizedException(String message) {
        super(message);
    }
}
