package org.siri_hate.main_service.model.dto.response.user;

/**
 * This class represents the full response for a user.
 * It includes all the details of a user that can be sent as a response.
 */
public class UserFullResponse {

    private Long id;
    private String username;

    /**
     * Default constructor.
     */
    public UserFullResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id       the id of the user
     * @param username the username of the user
     */
    public UserFullResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}