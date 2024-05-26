package org.siri_hate.user_service.model.dto.response;

public class SuccessfulAuthorizationResponse {

    String message;

    String token;

    public SuccessfulAuthorizationResponse(String token) {
        this.token = token;
    }

}
