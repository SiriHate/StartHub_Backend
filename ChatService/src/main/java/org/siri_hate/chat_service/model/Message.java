package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    @Indexed
    private String chatId;

    private String senderUsername;

    private String messageContent;

    @Indexed
    private LocalDateTime timestamp;

    public Message() { }

    public Message(String id, String chatId, String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.id = id;
        this.chatId = chatId;
        this.senderUsername = senderUsername;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}