package org.siri_hate.notification_service.exception;

/**
 * This class represents a custom exception that is thrown when an unknown email type is encountered.
 * It extends the RuntimeException class, meaning it is an unchecked exception.
 */
public class UnknownEmailTypeException extends RuntimeException {

    /**
     * Constructor for the UnknownEmailTypeException class.
     * It takes a message as a parameter and passes it to the superclass constructor.
     *
     * @param message the detail message associated with the exception
     */
    public UnknownEmailTypeException(String message) {
        super(message);
    }

}