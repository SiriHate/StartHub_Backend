package org.siri_hate.main_service.model.dto.response.project_members;

/**
 * This class represents a summary response for a project member.
 * It includes the essential details of a project member that can be sent as a response.
 */
public class ProjectMembersSummaryResponse {

    private Long id;
    private String username;
    private String role;

    /**
     * Default constructor.
     */
    public ProjectMembersSummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id       the id of the project member
     * @param username the username of the project member
     * @param role     the role of the project member
     */
    public ProjectMembersSummaryResponse(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    /**
     * @return the id of the project member
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
     * @return the username of the project member
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

    /**
     * @return the role of the project member
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}