package com.team2.slind.common.exception;

public class JudgementNotFoundException extends RuntimeException {
    public static final String NOT_EXIST_JUDGEMENT = "존재하지 않는 재판입니다.";
    public JudgementNotFoundException(String message) {super(message);}
}
