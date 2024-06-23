package org.siri_hate.notification_service.model.enums;

import lombok.Getter;

/**
 * This enum represents the subjects of the emails that can be sent.
 * It uses the @Getter annotation from Lombok to generate a getter method for the subject field.
 * Each enum constant is associated with a specific subject.
 */
@Getter
public enum EmailSubject {

    /**
     * Represents the subject of a registration confirmation email.
     */
    REGISTRATION_CONFIRMATION("Подтверждение регистрации на платформе StartHub"),

    /**
     * Represents the subject of a change password confirmation email.
     */
    CHANGE_PASSWORD_CONFIRMATION("Подтверждение смены пароля на платформе StartHub"),

    /**
     * Represents the subject of a successful registration notification email.
     */
    SUCCESSFUL_REGISTRATION_NOTIFICATION("Успешная регистрация на платформе StartHub"),

    /**
     * Represents the subject of a deleted account notification email.
     */
    DELETED_ACCOUNT_NOTIFICATION("Удаление аккаунта на платформе StartHub"),

    /**
     * Represents the subject of a changed password notification email.
     */
    CHANGED_PASSWORD_NOTIFICATION("Изменение пароля на платформе StartHub");

    /**
     * The subject of the email.
     */
    private final String subject;

    /**
     * Constructor for the EmailSubject enum.
     * It takes a subject as a parameter and assigns it to the subject field.
     *
     * @param subject the subject of the email
     */
    EmailSubject(String subject) {
        this.subject = subject;
    }

}