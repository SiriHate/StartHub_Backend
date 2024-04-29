package org.siri_hate.user_service.model.response;

import lombok.Data;

@Data
public class SuccessfulAuthorizationResponse {

    String message = "Authorization was successful";

    String token;

    public SuccessfulAuthorizationResponse(String token) {
        this.token = token;
    }

}
