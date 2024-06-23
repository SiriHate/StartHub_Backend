package org.siri_hate.main_service.exception;

/**
 * This class represents an exception that is thrown when a requested Article category does not exist.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class NoSuchArticleCategoryException extends RuntimeException {

    /**
     * Constructor for the NoSuchArticleCategoryException class.
     * It initializes the exception message.
     *
     * @param message the message describing the exception
     */
    public NoSuchArticleCategoryException(String message) {
        super(message);
    }

}