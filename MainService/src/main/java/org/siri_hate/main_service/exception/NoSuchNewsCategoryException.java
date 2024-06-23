package org.siri_hate.main_service.exception;

/**
 * This class represents an exception that is thrown when a requested News category does not exist.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class NoSuchNewsCategoryException extends RuntimeException {

    /**
     * Constructor for the NoSuchNewsCategoryException class.
     * It initializes the exception message.
     *
     * @param message the message describing the exception
     */
    public NoSuchNewsCategoryException(String message) {
        super(message);
    }

}