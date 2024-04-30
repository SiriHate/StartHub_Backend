package org.siri_hate.user_service.kafka;

import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, ConfirmationToken> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, ConfirmationToken> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendConfirmationToken(ConfirmationToken confirmationToken) {
        kafkaTemplate.send(topicName, confirmationToken);
    }

}
