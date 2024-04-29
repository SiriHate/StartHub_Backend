package org.siri_hate.user_service.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }

}
