package org.siri_hate.user_service.exception;


public class MismatchedPasswordException extends RuntimeException {

    
    public MismatchedPasswordException(String errorMessage) {
        super(errorMessage);
    }

}