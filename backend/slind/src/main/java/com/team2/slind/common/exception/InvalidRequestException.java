package com.team2.slind.common.exception;

public class InvalidRequestException extends RuntimeException {
    public static final String WRONG_REQUEST = "잘못된 요청입니다.";

    public InvalidRequestException(String message) {
        super(message);
    }
}
