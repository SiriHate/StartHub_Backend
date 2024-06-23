package org.siri_hate.user_service.model.dto.response.moderator;

/**
 * DTO for full moderator response.
 * This class is used to transfer data related to full moderator responses.
 * It contains an ID, a username, a name, and an employee ID.
 */
public class ModeratorFullResponse {

    /**
     * The ID of the moderator.
     * This is typically a unique identifier for the moderator.
     */
    private Long id;

    /**
     * The username of the moderator.
     * This is typically a unique identifier for the moderator in the system.
     */
    private String username;

    /**
     * The name of the moderator.
     * This is typically the real name of the moderator.
     */
    private String name;

    /**
     * The employee ID of the moderator.
     * This is typically a unique identifier for the moderator in the company.
     */
    private Long employeeId;

    /**
     * Default constructor.
     * Initializes a new instance of the ModeratorFullResponse class.
     */
    public ModeratorFullResponse() {}

    /**
     * Constructor with parameters.
     * Initializes a new instance of the ModeratorFullResponse class with an ID, a username, a name, and an employee ID.
     *
     * @param id The ID of the moderator.
     * @param username The username of the moderator.
     * @param name The name of the moderator.
     * @param employeeId The employee ID of the moderator.
     */
    public ModeratorFullResponse(Long id, String username, String name, Long employeeId) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.employeeId = employeeId;
    }

    /**
     * Gets the ID of the moderator.
     *
     * @return The ID of the moderator.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the moderator.
     *
     * @param id The ID of the moderator.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the moderator.
     *
     * @return The username of the moderator.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the moderator.
     *
     * @param username The username of the moderator.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the name of the moderator.
     *
     * @return The name of the moderator.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the moderator.
     *
     * @param name The name of the moderator.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the employee ID of the moderator.
     *
     * @return The employee ID of the moderator.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID of the moderator.
     *
     * @param employeeId The employee ID of the moderator.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

}