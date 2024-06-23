package org.siri_hate.user_service.model.dto.request.moderator;

/**
 * DTO for full moderator request.
 * This class is used to transfer data related to moderator requests.
 * It contains a username, password, name, and employee ID of the moderator.
 */
public class ModeratorFullRequest {

    /**
     * The username of the moderator.
     * This is typically a unique identifier for the moderator in the system.
     */
    private String username;

    /**
     * The password of the moderator.
     * This is typically a secret string that the moderator uses to authenticate themselves.
     */
    private String password;

    /**
     * The name of the moderator.
     * This is typically the full name of the moderator.
     */
    private String name;

    /**
     * The employee ID of the moderator.
     * This is typically a unique identifier for the moderator in the company.
     */
    private Long employeeId;

    /**
     * Default constructor for the ModeratorFullRequest class.
     */
    public ModeratorFullRequest() {}

    /**
     * Constructor for the ModeratorFullRequest class.
     *
     * @param username The username of the moderator.
     * @param password The password of the moderator.
     * @param name The name of the moderator.
     * @param employeeId The employee ID of the moderator.
     */
    public ModeratorFullRequest(String username, String password, String name, Long employeeId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.employeeId = employeeId;
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
     * Sets the password of the moderator.
     *
     * @param password The password of the moderator.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the password of the moderator.
     *
     * @return The password of the moderator.
     */
    public String getPassword() {
        return password;
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