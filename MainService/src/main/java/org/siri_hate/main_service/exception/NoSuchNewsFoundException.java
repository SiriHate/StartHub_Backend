package org.siri_hate.main_service.exception;


public class NoSuchNewsFoundException extends RuntimeException {


    public NoSuchNewsFoundException(String errorMessage) {
        super(errorMessage);
    }

}