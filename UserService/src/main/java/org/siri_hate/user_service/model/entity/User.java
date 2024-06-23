package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Objects;

/**
 * Entity for User.
 * This class is used to map the users table in the database.
 * It provides fields and methods specific to a User.
 * This class is abstract and should be extended by other classes to provide additional fields and methods.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

    /**
     * The ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The username of the user.
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The role of the user.
     */
    @Column(name = "role", nullable = false)
    private String role;

    /**
     * The status of the user's account.
     */
    @Column(name = "account_enabled", nullable = false)
    private boolean isEnabled;

    /**
     * Default constructor.
     */
    public User() {}

    /**
     * Constructor with parameters.
     *
     * @param id The ID of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     * @param isEnabled The status of the user's account.
     */
    public User(Long id, String username, String password, String role, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return The role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The role of the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the user's account is enabled.
     *
     * @return The status of the user's account.
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Sets the status of the user's account.
     *
     * @param enabled The status of the user's account.
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * Checks if the user is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isEnabled == user.isEnabled && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    /**
     * Generates a hash code for the user.
     *
     * @return The hash code of the user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, isEnabled);
    }

}