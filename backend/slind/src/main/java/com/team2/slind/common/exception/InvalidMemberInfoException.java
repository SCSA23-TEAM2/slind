package com.team2.slind.common.exception;

public class InvalidMemberInfoException extends RuntimeException {
    public static final String INVALID_USER_INFO_EXCEPTION = "유저 정보가 일치하지 않습니다.";

    public InvalidMemberInfoException(String message) {
        super(message);
    }
}
