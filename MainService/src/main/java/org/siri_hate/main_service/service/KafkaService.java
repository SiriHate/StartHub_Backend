package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.kafka.ProjectUpdateNotification;
import org.siri_hate.main_service.model.dto.kafka.UserDeletionMessage;

public interface KafkaService {
    void sendUserDeletionMessage(String username);
    void sendProjectUpdateNotification(ProjectUpdateNotification notification);
} 