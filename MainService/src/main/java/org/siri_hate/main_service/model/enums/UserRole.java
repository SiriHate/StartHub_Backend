package org.siri_hate.main_service.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    MEMBER("MEMBER"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

}
