package org.siri_hate.main_service.exception;

public class NoSuchArticleFoundException extends RuntimeException {

    public NoSuchArticleFoundException(String message) {
        super(message);
    }

}
