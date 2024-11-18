package com.team2.slind.common.exception;

public class InvalidMemberIdLengthException extends RuntimeException {
    public static final String INVALID_MEMBERID_LENGTH = "아이디는 4글자 이상, 16글자 이하만 가능합니다.";
    public InvalidMemberIdLengthException(String message) {
        super(message);
    }
}
