package org.siri_hate.user_service.exception;

public class NoSuchConfirmationTokenException extends RuntimeException {

    public NoSuchConfirmationTokenException(String errorMessage) {
        super(errorMessage);
    }

}
