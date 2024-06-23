package org.siri_hate.chat_service.model.dto.response.message;

import java.time.LocalDateTime;

/**
 * This class represents a full response for a message.
 * It contains the message's id, sender's username, message content, and the timestamp of the message.
 */
public class MessageSummaryResponse {

    /**
     * The id of the message.
     */
    private String id;

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
     * Default constructor for the MessageSummaryResponse class.
     */
    public MessageSummaryResponse() { }

    /**
     * Constructor for the MessageSummaryResponse class.
     * It initializes the id, sender's username, message content, and the timestamp.
     *
     * @param id the id of the message
     * @param senderUsername the username of the sender
     * @param messageContent the content of the message
     * @param timestamp the timestamp of the message
     */
    public MessageSummaryResponse(String id, String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    /**
     * Getter for the id.
     *
     * @return the id of the message
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id.
     *
     * @param id the new id of the message
     */
    public void setId(String id) {
        this.id = id;
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