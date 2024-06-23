package org.siri_hate.user_service.model.enums;

/**
 * Enum representing the type of confirmation message.
 * This enum is used to distinguish between different types of confirmation messages in the user service.
 *
 * <p>It has two values:</p>
 * <ul>
 * <li>REGISTRATION_CONFIRMATION: Represents a confirmation message for user registration.</li>
 * <li>CHANGE_PASSWORD_CONFIRMATION: Represents a confirmation message for changing the user password.</li>
 * </ul>
 */
public enum ConfirmationMessageType {

    /**
     * Represents a confirmation message for user registration.
     */
    REGISTRATION_CONFIRMATION,

    /**
     * Represents a confirmation message for changing the user password.
     */
    CHANGE_PASSWORD_CONFIRMATION;

}