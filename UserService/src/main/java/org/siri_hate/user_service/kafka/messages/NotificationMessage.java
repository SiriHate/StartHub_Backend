package org.siri_hate.user_service.kafka.messages;

import org.siri_hate.user_service.model.enums.NotificationMessageType;

public class NotificationMessage {

    private NotificationMessageType messageType;

    private String userFullName;

    private String userEmail;

    public NotificationMessage() {
    }

    public NotificationMessage(
            NotificationMessageType messageType, String userFullName, String userEmail) {
        this.messageType = messageType;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
    }

    public NotificationMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(NotificationMessageType messageType) {
        this.messageType = messageType;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
