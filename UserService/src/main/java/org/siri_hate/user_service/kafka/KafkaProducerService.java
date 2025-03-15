package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;

public interface KafkaProducerService {

  void sendConfirmationToken(ConfirmationMessage confirmationMessage);

  void sendNotification(NotificationMessage notificationMessage);
}
