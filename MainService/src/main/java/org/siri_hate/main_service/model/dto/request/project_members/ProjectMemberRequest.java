package org.siri_hate.main_service.model.dto.request.project_members;

import jakarta.validation.constraints.NotBlank;

/**
 * This class represents a request for a ProjectMember.
 * It includes fields for the username and role of the project member, both of which are mandatory.
 */
public class ProjectMemberRequest {

    @NotBlank(message = "Username is required")
    String username;

    @NotBlank(message = "Role should not be blank")
    String role;

    /**
     * Default constructor for ProjectMemberRequest.
     */
    public ProjectMemberRequest() { }

    /**
     * Constructor for ProjectMemberRequest with username and role fields.
     *
     * @param username the username of the project member
     * @param role     the role of the project member
     */
    public ProjectMemberRequest(String username, String role) {
        this.username = username;
        this.role = role;
    }

    /**
     * Returns the username of the project member.
     *
     * @return the username of the project member
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the project member.
     *
     * @param username the username of the project member
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the role of the project member.
     *
     * @return the role of the project member
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the project member.
     *
     * @param role the role of the project member
     */
    public void setRole(String role) {
        this.role = role;
    }

}