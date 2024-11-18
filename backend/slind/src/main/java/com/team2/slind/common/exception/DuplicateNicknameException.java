package com.team2.slind.common.exception;

public class DuplicateNicknameException extends RuntimeException {
    public static final String DUPLICATE_NICKNAME =
            "Duplicate Nickname";

    public DuplicateNicknameException(String message) {
        super(message);
    }
}
