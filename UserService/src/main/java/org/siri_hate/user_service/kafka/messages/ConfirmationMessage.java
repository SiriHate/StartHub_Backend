package org.siri_hate.user_service.kafka.messages;
import org.siri_hate.user_service.model.enums.ConfirmationMessageType;

import java.util.Objects;

public class ConfirmationMessage {

    private ConfirmationMessageType messageType;

    private String userFullName;

    private String userEmail;

    private String userConfirmationToken;

    public ConfirmationMessage() {}

    public ConfirmationMessage(ConfirmationMessageType messageType, String userFullName, String userEmail, String userConfirmationToken) {
        this.messageType = messageType;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userConfirmationToken = userConfirmationToken;
    }

    public ConfirmationMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(ConfirmationMessageType messageType) {
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

    public String getUserConfirmationToken() {
        return userConfirmationToken;
    }

    public void setUserConfirmationToken(String userConfirmationToken) {
        this.userConfirmationToken = userConfirmationToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmationMessage that = (ConfirmationMessage) o;
        return messageType == that.messageType && Objects.equals(userFullName, that.userFullName) && Objects.equals(userEmail, that.userEmail) && Objects.equals(userConfirmationToken, that.userConfirmationToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageType, userFullName, userEmail, userConfirmationToken);
    }

    @Override
    public String toString() {
        return "ConfirmationMessage{" +
                "messageType=" + messageType +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userConfirmationToken='" + userConfirmationToken + '\'' +
                '}';
    }

}
