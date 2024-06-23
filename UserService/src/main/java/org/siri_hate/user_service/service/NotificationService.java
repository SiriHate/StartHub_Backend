package org.siri_hate.user_service.service;

/**
 * Notification service interface.
 * This interface defines the contract for notification operations.
 * It provides methods for sending successful registration, account deletion, and password change notifications.
 */
public interface NotificationService {

    /**
     * Sends a successful registration notification.
     * This method sends a successful registration notification to a user.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    void sendSuccessfulRegistrationNotification(String name, String email);

    /**
     * Sends an account deletion notification.
     * This method sends an account deletion notification to a user.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    void sendDeletedAccountNotification(String name, String email);

    /**
     * Sends a password change notification.
     * This method sends a password change notification to a user.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    void sendChangedPasswordNotification(String name, String email);

}