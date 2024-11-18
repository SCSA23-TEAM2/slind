package com.team2.slind.common.exception;

public class NoReactionExcetpion extends RuntimeException{
    public static final String NO_REACTION_FOR_DOWN_IN_ARTICLE = "본 게시물에 좋아요/싫어요를 누른 적이 없습니다.";
    public static final String NO_REACTION_FOR_DOWN_IN_COMMENT = "본 게시물에 좋아요/싫어요를 누른 적이 없습니다.";

    public NoReactionExcetpion(String message) {super(message);}
}
