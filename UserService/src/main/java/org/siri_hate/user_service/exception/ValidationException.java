package org.siri_hate.user_service.exception;

/**
 * Custom exception class for validation errors.
 * This class extends the RuntimeException class, meaning it is an unchecked exception.
 * Unchecked exceptions are exceptions that can be thrown during the execution of the program and are not checked at compile time.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructor for the ValidationException class.
     * This constructor takes a string message as an argument, which is passed to the superclass constructor.
     * The message is used to provide details about the exception.
     *
     * @param errorMessage the detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

}