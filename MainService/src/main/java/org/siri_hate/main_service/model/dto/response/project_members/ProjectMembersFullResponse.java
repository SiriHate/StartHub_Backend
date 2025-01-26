package org.siri_hate.main_service.model.dto.response.project_members;


public class ProjectMembersFullResponse {

    private Long id;
    private String username;
    private String role;


    public ProjectMembersFullResponse() { }


    public ProjectMembersFullResponse(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

}