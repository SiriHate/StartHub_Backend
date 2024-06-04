package org.siri_hate.user_service.model.dto.response.admin;

public class AdminFullResponse {

    Long id;

    String username;

    public AdminFullResponse() { }

    public AdminFullResponse(Long id, String username) {
        this.id = id;
        this.username = username;
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

}