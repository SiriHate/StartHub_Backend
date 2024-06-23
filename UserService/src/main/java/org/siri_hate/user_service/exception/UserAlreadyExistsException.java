package org.siri_hate.user_service.exception;

/**
 * Exception thrown when a user already exists.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is an attempt to create a user that already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructor for the UserAlreadyExistsException class.
     *
     * @param errorMessage The error message that will be associated with the exception.
     */
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}