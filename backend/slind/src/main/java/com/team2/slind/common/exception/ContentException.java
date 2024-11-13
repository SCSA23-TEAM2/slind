package com.team2.slind.common.exception;

public class ContentException extends RuntimeException {
    public static final String EMPTY_CONTENT = "내용을 입력해주세요.";
    public static final String CONTENT_TOO_LONG = "내용은 1000자 이하로 입력해주세요.";

    public ContentException(String message) {
        super(message);
    }
}
