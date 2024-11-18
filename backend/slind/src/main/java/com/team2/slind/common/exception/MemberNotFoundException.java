package com.team2.slind.common.exception;

public class MemberNotFoundException extends RuntimeException{
    public static final String MEMBER_NOT_FOUND = "Member Not Found";

    public MemberNotFoundException(String message) {
        super(message);
    }
}
