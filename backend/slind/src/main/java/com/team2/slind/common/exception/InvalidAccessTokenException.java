package com.team2.slind.common.exception;

public class InvalidAccessTokenException extends RuntimeException{
    public static final String INVALID_ACCESS_TOKEN = "액세스 토큰 유효하지 않음.";
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
