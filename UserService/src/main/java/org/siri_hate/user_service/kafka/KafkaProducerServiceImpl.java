package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${notification.topic.name}")
    private String notificationTopicName;

    @Value("${confirmation.topic.name}")
    private String confirmationTopicName;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendConfirmationToken(ConfirmationMessage confirmationMessage) {
        kafkaTemplate.send(confirmationTopicName, confirmationMessage);
    }

    @Override
    public void sendNotification(NotificationMessage notificationMessage) {
        kafkaTemplate.send(notificationTopicName, notificationMessage);
    }

}
