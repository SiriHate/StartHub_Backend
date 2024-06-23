package org.siri_hate.user_service.exception;

/**
 * Exception thrown when authentication fails.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is an issue with user authentication, such as incorrect credentials.
 */
public class AuthenticationException extends RuntimeException {

    /**
     * Constructor for the AuthenticationException class.
     *
     * @param errorMessage The error message that will be associated with the exception.
     */
    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }

}