package org.siri_hate.user_service.model.enums;

/**
 * Enum representing the type of notification message.
 * This enum is used to distinguish between different types of notification messages in the user service.
 *
 * <p>It has three values:</p>
 * <ul>
 * <li>SUCCESSFUL_REGISTRATION_NOTIFICATION: Represents a notification message for successful user registration.</li>
 * <li>DELETED_ACCOUNT_NOTIFICATION: Represents a notification message for a deleted user account.</li>
 * <li>CHANGED_PASSWORD_NOTIFICATION: Represents a notification message for a changed user password.</li>
 * </ul>
 */
public enum NotificationMessageType {

    /**
     * Represents a notification message for successful user registration.
     */
    SUCCESSFUL_REGISTRATION_NOTIFICATION,

    /**
     * Represents a notification message for a deleted user account.
     */
    DELETED_ACCOUNT_NOTIFICATION,

    /**
     * Represents a notification message for a changed user password.
     */
    CHANGED_PASSWORD_NOTIFICATION;

}