package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.kafka.messages.NotificationMessage;
import org.siri_hate.user_service.kafka.messages.ProjectUpdateNotification;

public interface KafkaProducerService {

  void sendConfirmationToken(ConfirmationMessage confirmationMessage);

  void sendNotification(NotificationMessage notificationMessage);

  void sendUserDeletionMessage(String username);

  void sendProjectUpdateNotification(ProjectUpdateNotification notification);
}
