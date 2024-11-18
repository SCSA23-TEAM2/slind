package com.team2.slind.common.exception;

public class InvalidRefreshTokenException extends RuntimeException{
    public static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}
