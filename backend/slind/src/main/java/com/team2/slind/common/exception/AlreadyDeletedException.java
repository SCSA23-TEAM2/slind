package com.team2.slind.common.exception;

public class AlreadyDeletedException extends RuntimeException {
    public static final String ALREADY_DELETED_BOARD = "ALREADY_DELETED_BOARD";

    public AlreadyDeletedException(String message) {
        super(message);
    }
}
