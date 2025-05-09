package org.siri_hate.chat_service.exception;

public class ChatAlreadyExistsException extends RuntimeException {
    public ChatAlreadyExistsException(String message) {
        super(message);
    }
} 