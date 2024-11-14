package com.team2.slind.common.exception;

public class LastMonthCreationException extends RuntimeException {
    public static final String LAST_MONTH_CREATION = "이번 달 게시판 생성 횟수를 모두 소진했습니다.";

    public LastMonthCreationException(String message) {
        super(message);
    }
}
