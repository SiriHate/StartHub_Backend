package org.siri_hate.user_service.exception;

/**
 * Exception thrown when the request body is bad.
 * This class extends the RuntimeException class, meaning it's an unchecked exception.
 * It is thrown when there is an issue with the request body, such as missing or incorrect data.
 */
public class BadRequestBodyException extends RuntimeException {

    /**
     * Constructor for the BadRequestBody class.
     *
     * @param message The error message that will be associated with the exception.
     */
    public BadRequestBodyException(String message) {
        super(message);
    }

}