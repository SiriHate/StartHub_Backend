package org.siri_hate.main_service.exception;


public class NoSuchUserException extends RuntimeException {


    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }

}