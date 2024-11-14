package com.team2.slind.common.exception;

public class FailedReactionException extends RuntimeException {
    public static final String FAILED_REACTION = "좋아요/싫어요에 실패했습니다.";
    public FailedReactionException(String message) {
        super(message);
    }
}
