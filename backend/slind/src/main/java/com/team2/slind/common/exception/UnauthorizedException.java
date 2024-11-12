package com.team2.slind.common.exception;

public class UnauthorizedException extends RuntimeException {
    public static final String UNAUTHORIZED_DELETE_BOARD = "게시판 삭제 권한이 없는 계정입니다.";

    public UnauthorizedException(String message) {
        super(message);
    }
}
