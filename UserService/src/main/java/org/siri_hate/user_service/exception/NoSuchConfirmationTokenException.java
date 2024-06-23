package org.siri_hate.user_service.exception;

/**
 * Exception thrown when no such confirmation token exists.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is an attempt to access a confirmation token that does not exist.
 */
public class NoSuchConfirmationTokenException extends RuntimeException {

    /**
     * Constructor for the NoSuchConfirmationTokenException class.
     *
     * @param errorMessage The error message that will be associated with the exception.
     */
    public NoSuchConfirmationTokenException(String errorMessage) {
        super(errorMessage);
    }

}