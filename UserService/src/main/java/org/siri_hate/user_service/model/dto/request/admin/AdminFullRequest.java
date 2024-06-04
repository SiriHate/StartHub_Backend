package org.siri_hate.user_service.model.dto.request.admin;

public class AdminFullRequest {

    String username;

    String password;

    public AdminFullRequest() { }

    public AdminFullRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}