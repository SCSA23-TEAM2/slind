package com.team2.slind.common.exception;

public class AlreadyDeletedException extends RuntimeException {
    public static final String ALREADY_DELETED_BOARD = "ALREADY_DELETED_BOARD";
    public static final String ALREADY_DELETED_ARTICLE = "ALREADY_DELETED_ARTICLE";
    public static final String ALREADY_DELETED_COMMENT = "ALREADY_DELETED_COMMENT";

    public AlreadyDeletedException(String message) {
        super(message);
    }
}
