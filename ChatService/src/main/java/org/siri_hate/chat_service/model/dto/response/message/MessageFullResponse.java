package org.siri_hate.chat_service.model.dto.response.message;

import java.time.LocalDateTime;

public class MessageFullResponse {

    private String id;

    private String senderUsername;

    private String messageContent;

    private LocalDateTime timestamp;

    public MessageFullResponse() { }

    public MessageFullResponse(String id, String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.id = id;
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
