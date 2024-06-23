package org.siri_hate.user_service.service.impl;

import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.siri_hate.user_service.model.enums.NotificationMessageType;
import org.siri_hate.user_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Notification service implementation class.
 * This class implements the NotificationService interface and provides the business logic for notification operations.
 * It uses the KafkaProducerService to send notifications.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    // Kafka producer service instance
    private final KafkaProducerService kafkaProducerService;

    /**
     * Constructor for the NotificationServiceImpl class.
     * This constructor initializes the KafkaProducerService.
     *
     * @param kafkaProducerService the Kafka producer service
     */
    @Autowired
    public NotificationServiceImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Sends a successful registration notification.
     * This method creates a successful registration notification message and sends it using the Kafka producer service.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    @Override
    public void sendSuccessfulRegistrationNotification(String name, String email) {
        NotificationMessageType messageType = NotificationMessageType.SUCCESSFUL_REGISTRATION_NOTIFICATION;
        NotificationMessage notificationMessage = new NotificationMessage(messageType, name, email);
        kafkaProducerService.sendNotification(notificationMessage);
    }

    /**
     * Sends a deleted account notification.
     * This method creates a deleted account notification message and sends it using the Kafka producer service.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    @Override
    public void sendDeletedAccountNotification(String name, String email) {
        NotificationMessageType messageType = NotificationMessageType.DELETED_ACCOUNT_NOTIFICATION;
        NotificationMessage notificationMessage = new NotificationMessage(messageType, name, email);
        kafkaProducerService.sendNotification(notificationMessage);
    }

    /**
     * Sends a changed password notification.
     * This method creates a changed password notification message and sends it using the Kafka producer service.
     *
     * @param name the name of the user
     * @param email the email of the user
     */
    @Override
    public void sendChangedPasswordNotification(String name, String email) {
        NotificationMessageType messageType = NotificationMessageType.CHANGED_PASSWORD_NOTIFICATION;
        NotificationMessage notificationMessage = new NotificationMessage(messageType, name, email);
        kafkaProducerService.sendNotification(notificationMessage);
    }

}