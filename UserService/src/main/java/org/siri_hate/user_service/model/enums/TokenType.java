package org.siri_hate.user_service.model.enums;

/**
 * Enum representing the type of token.
 * This enum is used to distinguish between different types of tokens in the user service.
 *
 * <p>It has two values:</p>
 * <ul>
 * <li>CONFIRM_REGISTRATION: Represents a token for confirming user registration.</li>
 * <li>CONFIRM_CHANGE_PASSWORD: Represents a token for confirming a password change.</li>
 * </ul>
 */
public enum TokenType {

    /**
     * Represents a token for confirming user registration.
     */
    CONFIRM_REGISTRATION("CONFIRM_REGISTRATION"),

    /**
     * Represents a token for confirming a password change.
     */
    CONFIRM_CHANGE_PASSWORD("CONFIRM_CHANGE_PASSWORD");

    private final String value;

    /**
     * Constructor for the TokenType enum.
     *
     * @param value The value that will be associated with the enum.
     */
    private TokenType(String value) {
        this.value = value;
    }

    /**
     * Getter for the value of the TokenType enum.
     *
     * @return The value associated with the enum.
     */
    public String getValue() {
        return value;
    }

}