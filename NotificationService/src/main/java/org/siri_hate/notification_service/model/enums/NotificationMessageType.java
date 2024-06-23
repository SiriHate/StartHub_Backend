package org.siri_hate.notification_service.model.enums;

import lombok.Getter;

/**
 * This enum represents the types of notification messages that can be sent.
 * It uses the @Getter annotation from Lombok to generate getter methods for the enum constants.
 * The enum constants are SUCCESSFUL_REGISTRATION_NOTIFICATION, DELETED_ACCOUNT_NOTIFICATION, and CHANGED_PASSWORD_NOTIFICATION.
 */
@Getter
public enum NotificationMessageType {

    /**
     * Represents a successful registration notification message.
     */
    SUCCESSFUL_REGISTRATION_NOTIFICATION,

    /**
     * Represents a deleted account notification message.
     */
    DELETED_ACCOUNT_NOTIFICATION,

    /**
     * Represents a changed password notification message.
     */
    CHANGED_PASSWORD_NOTIFICATION;

}