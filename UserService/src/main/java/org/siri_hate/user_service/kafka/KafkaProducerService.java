package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.model.entity.ConfirmationToken;

public interface KafkaProducerService {

    void sendConfirmationToken(ConfirmationToken confirmationToken);

}
