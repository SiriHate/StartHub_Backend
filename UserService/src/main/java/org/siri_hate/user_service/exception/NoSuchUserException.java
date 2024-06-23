package org.siri_hate.user_service.exception;

/**
 * Exception thrown when no such user exists.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is an attempt to access a user that does not exist.
 */
public class NoSuchUserException extends RuntimeException {

    /**
     * Constructor for the NoSuchUserException class.
     *
     * @param errorMessage The error message that will be associated with the exception.
     */
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }

}