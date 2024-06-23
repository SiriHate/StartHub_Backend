package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

/**
 * This class represents a message.
 * It contains the message's id, chat id, sender's username, message content, and the timestamp of the message.
 */
@Document(collection = "messages")
public class Message {

    /**
     * The id of the message.
     */
    @Id
    private String id;

    /**
     * The id of the chat that the message belongs to.
     */
    @Indexed
    private String chatId;

    /**
     * The username of the sender of the message.
     */
    private String senderUsername;

    /**
     * The content of the message.
     */
    private String messageContent;

    /**
     * The timestamp of the message.
     */
    @Indexed
    private LocalDateTime timestamp;

    /**
     * Default constructor for the Message class.
     */
    public Message() { }

    /**
     * Constructor for the Message class.
     * It initializes the id, chat id, sender's username, message content, and the timestamp.
     *
     * @param id the id of the message
     * @param chatId the id of the chat that the message belongs to
     * @param senderUsername the username of the sender of the message
     * @param messageContent the content of the message
     * @param timestamp the timestamp of the message
     */
    public Message(String id, String chatId, String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.id = id;
        this.chatId = chatId;
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
     * Getter for the chat id.
     *
     * @return the id of the chat that the message belongs to
     */
    public String getChatId() {
        return chatId;
    }

    /**
     * Setter for the chat id.
     *
     * @param chatId the new id of the chat that the message belongs to
     */
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for the sender's username.
     *
     * @return the username of the sender of the message
     */
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * Setter for the sender's username.
     *
     * @param senderUsername the new username of the sender of the message
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