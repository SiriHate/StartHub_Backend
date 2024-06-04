package org.siri_hate.chat_service.model.dto.request;

import java.time.LocalDateTime;

public class SendMessageRequest {

    private String senderUsername;

    private String messageContent;

    private LocalDateTime timestamp;

    public SendMessageRequest() { }

    public SendMessageRequest(String senderUsername, String messageContent, LocalDateTime timestamp) {
        this.senderUsername = senderUsername;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
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
