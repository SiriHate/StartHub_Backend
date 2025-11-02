package org.siri_hate.notification_service.model.enums;

import lombok.Getter;

@Getter
public enum EmailSubject {

    REGISTRATION_CONFIRMATION("Подтверждение регистрации на платформе StartHub"),
    CHANGE_PASSWORD_CONFIRMATION("Подтверждение смены пароля на платформе StartHub"),
    SUCCESSFUL_REGISTRATION_NOTIFICATION("Успешная регистрация на платформе StartHub"),
    DELETED_ACCOUNT_NOTIFICATION("Удаление аккаунта на платформе StartHub"),
    CHANGED_PASSWORD_NOTIFICATION("Изменение пароля на платформе StartHub");

    private final String subject;

    EmailSubject(String subject) {
        this.subject = subject;
    }
}
