package org.siri_hate.notification_service.kafka;

import jakarta.mail.MessagingException;

public interface KafkaConsumerService {

   void NotificationTopicListener(String message) throws MessagingException;

   void ConfirmationTopicListener(String message) throws MessagingException;

}
