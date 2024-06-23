package org.siri_hate.user_service.model.enums;

/**
 * Enum representing the role of a user.
 * This enum is used to distinguish between different roles of users in the user service.
 *
 * <p>It has three values:</p>
 * <ul>
 * <li>MEMBER: Represents a regular user in the system.</li>
 * <li>MODERATOR: Represents a user with additional permissions, such as moderating user content.</li>
 * <li>ADMIN: Represents a user with full permissions, including user and system management.</li>
 * </ul>
 */
public enum UserRole {

    /**
     * Represents a regular user in the system.
     */
    MEMBER("MEMBER"),

    /**
     * Represents a user with additional permissions, such as moderating user content.
     */
    MODERATOR("MODERATOR"),

    /**
     * Represents a user with full permissions, including user and system management.
     */
    ADMIN("ADMIN");

    private final String value;

    /**
     * Constructor for the UserRole enum.
     *
     * @param value The value that will be associated with the enum.
     */
    UserRole(String value) {
        this.value = value;
    }

    /**
     * Getter for the value of the UserRole enum.
     *
     * @return The value associated with the enum.
     */
    public String getValue() {
        return value;
    }

}