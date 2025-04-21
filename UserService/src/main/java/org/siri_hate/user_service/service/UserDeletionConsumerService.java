package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.kafka.UserDeletionMessage;

public interface UserDeletionConsumerService {
    void consumeUserDeletionMessage(String message);
} 