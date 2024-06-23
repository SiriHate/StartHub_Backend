package org.siri_hate.user_service.exception;

/**
 * Exception thrown when the provided passwords do not match.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is a mismatch between the provided password and the confirmed password.
 */
public class MismatchedPasswordException extends RuntimeException {

    /**
     * Constructor for the MismatchedPasswordException class.
     *
     * @param errorMessage The error message that will be associated with the exception.
     */
    public MismatchedPasswordException(String errorMessage) {
        super(errorMessage);
    }

}