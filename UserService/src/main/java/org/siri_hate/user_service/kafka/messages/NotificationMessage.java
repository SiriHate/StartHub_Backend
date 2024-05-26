package org.siri_hate.user_service.kafka.messages;

import org.siri_hate.user_service.model.enums.NotificationMessageType;

import java.util.Objects;

public class NotificationMessage {

    private NotificationMessageType messageType;

    private String userFullName;

    private String userEmail;

    public NotificationMessage() {}

    public NotificationMessage(NotificationMessageType messageType, String userFullName, String userEmail) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationMessage that = (NotificationMessage) o;
        return messageType == that.messageType && Objects.equals(userFullName, that.userFullName) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageType, userFullName, userEmail);
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "messageType=" + messageType +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

}
