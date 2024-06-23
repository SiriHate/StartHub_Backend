package org.siri_hate.user_service.kafka.messages;

import org.siri_hate.user_service.model.enums.NotificationMessageType;

/**
 * Class representing a notification message.
 * This class is used to transfer data related to notification messages.
 * It contains the message type, user's full name, and user's email.
 */
public class NotificationMessage {

    /**
     * The type of the notification message.
     * This is typically an enum value representing the type of the notification.
     */
    private NotificationMessageType messageType;

    /**
     * The full name of the user.
     * This is typically the full name of the user who will receive the notification.
     */
    private String userFullName;

    /**
     * The email of the user.
     * This is typically the email address of the user who will receive the notification.
     */
    private String userEmail;

    /**
     * Default constructor for the NotificationMessage class.
     */
    public NotificationMessage() {}

    /**
     * Constructor for the NotificationMessage class.
     *
     * @param messageType The type of the notification message.
     * @param userFullName The full name of the user.
     * @param userEmail The email of the user.
     */
    public NotificationMessage(NotificationMessageType messageType, String userFullName, String userEmail) {
        this.messageType = messageType;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
    }

    /**
     * Gets the type of the notification message.
     *
     * @return The type of the notification message.
     */
    public NotificationMessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the type of the notification message.
     *
     * @param messageType The type of the notification message.
     */
    public void setMessageType(NotificationMessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Gets the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param userFullName The full name of the user.
     */
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the email of the user.
     *
     * @param userEmail The email of the user.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}