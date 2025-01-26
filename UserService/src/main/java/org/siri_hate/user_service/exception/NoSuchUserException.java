package org.siri_hate.user_service.exception;


public class NoSuchUserException extends RuntimeException {

    
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }

}