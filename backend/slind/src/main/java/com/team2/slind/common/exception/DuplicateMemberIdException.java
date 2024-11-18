package com.team2.slind.common.exception;

public class DuplicateMemberIdException extends RuntimeException {
    public static final String DUPLICATE_MEMBERID = "Duplicate MemberId";

    public DuplicateMemberIdException(String message) {
        super(message);
    }
}
