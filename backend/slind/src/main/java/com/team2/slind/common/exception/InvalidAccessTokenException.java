package com.team2.slind.common.exception;

public class InvalidAccessTokenException extends RuntimeException{
    public static final String INVALID_ACCESS_TOKEN = "Invalid access token";
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
