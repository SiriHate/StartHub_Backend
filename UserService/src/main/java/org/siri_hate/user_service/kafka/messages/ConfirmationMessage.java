package org.siri_hate.user_service.kafka.messages;

import org.siri_hate.user_service.model.enums.ConfirmationMessageType;

/**
 * Class representing a confirmation message.
 * This class is used to transfer data related to confirmation messages.
 * It contains the message type, user's full name, user's email, and user's confirmation token.
 */
public class ConfirmationMessage {

    /**
     * The type of the confirmation message.
     * This is typically an enum value representing the type of the confirmation.
     */
    private ConfirmationMessageType messageType;

    /**
     * The full name of the user.
     * This is typically the full name of the user who will receive the confirmation.
     */
    private String userFullName;

    /**
     * The email of the user.
     * This is typically the email address of the user who will receive the confirmation.
     */
    private String userEmail;

    /**
     * The confirmation token of the user.
     * This is typically a unique string that the user uses to confirm their action.
     */
    private String userConfirmationToken;

    /**
     * Default constructor for the ConfirmationMessage class.
     */
    public ConfirmationMessage() {}

    /**
     * Constructor for the ConfirmationMessage class.
     *
     * @param messageType The type of the confirmation message.
     * @param userFullName The full name of the user.
     * @param userEmail The email of the user.
     * @param userConfirmationToken The confirmation token of the user.
     */
    public ConfirmationMessage(ConfirmationMessageType messageType, String userFullName, String userEmail, String userConfirmationToken) {
        this.messageType = messageType;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userConfirmationToken = userConfirmationToken;
    }

    /**
     * Gets the type of the confirmation message.
     *
     * @return The type of the confirmation message.
     */
    public ConfirmationMessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the type of the confirmation message.
     *
     * @param messageType The type of the confirmation message.
     */
    public void setMessageType(ConfirmationMessageType messageType) {
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

    /**
     * Gets the confirmation token of the user.
     *
     * @return The confirmation token of the user.
     */
    public String getUserConfirmationToken() {
        return userConfirmationToken;
    }

    /**
     * Sets the confirmation token of the user.
     *
     * @param userConfirmationToken The confirmation token of the user.
     */
    public void setUserConfirmationToken(String userConfirmationToken) {
        this.userConfirmationToken = userConfirmationToken;
    }

}