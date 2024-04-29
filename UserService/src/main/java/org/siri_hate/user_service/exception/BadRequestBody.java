package org.siri_hate.user_service.exception;

public class BadRequestBody extends RuntimeException {

    public BadRequestBody(String message) {
        super(message);
    }

}
