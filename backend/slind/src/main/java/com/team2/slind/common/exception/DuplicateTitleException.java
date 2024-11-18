package com.team2.slind.common.exception;

public class DuplicateTitleException extends RuntimeException {
    public static final String DUPLICATE_BOARD_TITLE = "Duplicate Board Title";

    public DuplicateTitleException(String message) {
        super(message);
    }
}
