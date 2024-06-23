package org.siri_hate.chat_service.exception;

/**
 * This class represents a custom exception for the scenario when a chat is not found.
 * It extends the RuntimeException class.
 */
public class NoSuchChatException extends RuntimeException {

    /**
     * Constructor for the NoSuchChatException class.
     * It initializes the exception message.
     *
     * @param message the exception message
     */
    public NoSuchChatException(String message) {
        super(message);
    }

}