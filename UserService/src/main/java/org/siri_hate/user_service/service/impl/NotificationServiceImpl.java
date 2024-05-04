package org.siri_hate.user_service.service.impl;

import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.siri_hate.user_service.model.enums.NotificationMessageType;
import org.siri_hate.user_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public NotificationServiceImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void sendDeletedAccountMessage(String name, String email) {
        NotificationMessageType messageType = NotificationMessageType.DELETED_ACCOUNT_NOTIFICATION;
        NotificationMessage notificationMessage = new NotificationMessage(messageType, name, email);
        kafkaProducerService.sendNotification(notificationMessage);
    }

}
