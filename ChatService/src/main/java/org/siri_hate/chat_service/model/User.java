package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents a user.
 * It contains the user's id and username.
 */
@Document(collection = "users")
public class User {

    /**
     * The id of the user.
     */
    @Id
    private String id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * Default constructor for the User class.
     */
    public User() { }

    /**
     * Constructor for the User class.
     * It initializes the username.
     *
     * @param username the username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Getter for the id.
     *
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id.
     *
     * @param id the new id of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the username.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

}