package com.team2.slind.common.exception;

public class InvalidTokenException extends RuntimeException{
    public static final String INVALID_NICKNAME_LENGTH = "닉네임은 1글자 이상, 16글자 이하만 가능합니다.";

    public InvalidTokenException(String message) {
        super(message);
    }
}
