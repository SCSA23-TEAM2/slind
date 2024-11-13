package com.team2.slind.common.exception;

public class ArticleNullException extends RuntimeException {
    public static final String NULL_CONTENT = "NO CONTENT IN ARTICLE";
    public static final String NULL_TITLE = "NO TITLE IN ARTICLE";

    public ArticleNullException(String message) {super(message);}
}
