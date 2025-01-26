package org.siri_hate.main_service.exception;


public class NoSuchProjectFoundException extends RuntimeException {


    public NoSuchProjectFoundException(String errorMessage) {
        super(errorMessage);
    }

}