package org.siri_hate.user_service.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

}
