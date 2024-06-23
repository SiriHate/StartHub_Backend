package org.siri_hate.chat_service.model.dto.request;

import java.time.LocalDateTime;

/**
 * This class represents a request to send a message.
 * It contains the sender's username, the message content, and the timestamp of the message.
 */
public class SendMessageRequest {

    /**
     * The username of the sender.
     */
    private String senderUsername;

    /**
     * The content of the message.
     */
    private String messageContent;

    /**
     * The timestamp of the message.
     */
    private LocalDateTime timestamp;

    /**
     * Default constructor for the SendMessageRequest class.
     */
    public SendMessageRequest() { }

    /**
     * Constructor for the SendMessageRequest class.
     * It initializes the sender's username, the message content, and the timestamp.
     *
     * @param senderUsername the username of the sender
     * @param messageContent the content of the message
     * @param timestamp the timestamp of the message
     */
    public SendMessageRequest(String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.senderUsername = senderUsername;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    /**
     * Getter for the sender's username.
     *
     * @return the username of the sender
     */
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * Setter for the sender's username.
     *
     * @param senderUsername the new username of the sender
     */
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    /**
     * Getter for the message content.
     *
     * @return the content of the message
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Setter for the message content.
     *
     * @param messageContent the new content of the message
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * Getter for the timestamp.
     *
     * @return the timestamp of the message
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for the timestamp.
     *
     * @param timestamp the new timestamp of the message
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}