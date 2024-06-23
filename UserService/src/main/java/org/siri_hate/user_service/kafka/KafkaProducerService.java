package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.siri_hate.user_service.model.entity.ConfirmationToken;

/**
 * Interface for Kafka producer service.
 * This service is responsible for sending messages to Kafka topics.
 */
public interface KafkaProducerService {

    /**
     * Sends a confirmation token message to a Kafka topic.
     *
     * @param confirmationMessage The confirmation message to be sent.
     */
    void sendConfirmationToken(ConfirmationMessage confirmationMessage);

    /**
     * Sends a notification message to a Kafka topic.
     *
     * @param notificationMessage The notification message to be sent.
     */
    void sendNotification(NotificationMessage notificationMessage);

}
