package org.siri_hate.main_service.exception;

/**
 * This class represents an exception that is thrown when a requested Project category does not exist.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class NoSuchProjectCategoryException extends RuntimeException {

    /**
     * Constructor for the NoSuchProjectCategoryException class.
     * It initializes the exception message.
     *
     * @param message the message describing the exception
     */
    public NoSuchProjectCategoryException(String message) {
        super(message);
    }

}