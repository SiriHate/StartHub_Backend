package org.siri_hate.main_service.exception;

/**
 * This class represents an exception that is thrown when a requested Project does not exist.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class NoSuchProjectFoundException extends RuntimeException {

    /**
     * Constructor for the NoSuchProjectFoundException class.
     * It initializes the exception message.
     *
     * @param errorMessage the message describing the exception
     */
    public NoSuchProjectFoundException(String errorMessage) {
        super(errorMessage);
    }

}