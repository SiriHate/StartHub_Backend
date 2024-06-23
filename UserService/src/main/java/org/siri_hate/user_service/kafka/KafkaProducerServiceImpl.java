package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Kafka producer service.
 * This service is responsible for sending messages to Kafka topics.
 */
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    /**
     * Kafka template for sending messages.
     */
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Topic name for sending notification messages.
     */
    @Value("${notification.topic.name}")
    private String notificationTopicName;

    /**
     * Topic name for sending confirmation messages.
     */
    @Value("${confirmation.topic.name}")
    private String confirmationTopicName;

    /**
     * Constructs a KafkaProducerServiceImpl with the provided KafkaTemplate.
     *
     * @param kafkaTemplate The KafkaTemplate for sending messages.
     */
    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a confirmation token message to a Kafka topic.
     *
     * @param confirmationMessage The confirmation message to be sent.
     */
    @Override
    public void sendConfirmationToken(ConfirmationMessage confirmationMessage) {
        kafkaTemplate.send(confirmationTopicName, confirmationMessage);
    }

    /**
     * Sends a notification message to a Kafka topic.
     *
     * @param notificationMessage The notification message to be sent.
     */
    @Override
    public void sendNotification(NotificationMessage notificationMessage) {
        kafkaTemplate.send(notificationTopicName, notificationMessage);
    }

}
