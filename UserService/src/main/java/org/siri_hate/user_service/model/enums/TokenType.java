package org.siri_hate.user_service.model.enums;

import lombok.Getter;

@Getter
public enum TokenType {

    CONFIRM_REGISTRATION("CONFIRM_REGISTRATION"),
    CONFIRM_CHANGE_PASSWORD("CONFIRM_CHANGE_PASSWORD");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

}

