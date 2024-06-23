package org.siri_hate.main_service.exception;

/**
 * This class represents an exception that is thrown when a requested Article does not exist.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class NoSuchArticleFoundException extends RuntimeException {

    /**
     * Constructor for the NoSuchArticleFoundException class.
     * It initializes the exception message.
     *
     * @param message the message describing the exception
     */
    public NoSuchArticleFoundException(String message) {
        super(message);
    }

}