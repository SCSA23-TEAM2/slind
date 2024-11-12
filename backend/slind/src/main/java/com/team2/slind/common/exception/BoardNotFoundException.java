package com.team2.slind.common.exception;

public class BoardNotFoundException extends RuntimeException {
    public static final String BOARD_NOT_FOUND = "Board not found";

    public BoardNotFoundException(String message) {
        super(message);
    }
}
