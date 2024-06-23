package org.siri_hate.chat_service.exception;

/**
 * This class represents a custom exception for the scenario when a user is not found.
 * It extends the RuntimeException class.
 */
public class NoSuchUserException extends RuntimeException {

    /**
     * Constructor for the NoSuchUserException class.
     * It initializes the exception message.
     *
     * @param errorMessage the exception message
     */
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }

}