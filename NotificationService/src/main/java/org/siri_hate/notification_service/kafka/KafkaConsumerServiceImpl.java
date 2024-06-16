package org.siri_hate.notification_service.kafka;

import jakarta.mail.MessagingException;
import org.siri_hate.notification_service.service.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final MailSenderServiceImpl mailSenderService;

    @Autowired
    public KafkaConsumerServiceImpl(MailSenderServiceImpl mailSenderService) {
        this.mailSenderService = mailSenderService;
    }


    @Override
    @KafkaListener(topics = "notification_topic", groupId = "consumers")
    public void NotificationTopicListener(String message) throws MessagingException {
            mailSenderService.sendNotificationMail(message);
    }

    @Override
    @KafkaListener(topics = "confirmation_topic", groupId = "consumers")
    public void ConfirmationTopicListener(String message) throws MessagingException {
        mailSenderService.sendConfirmationMail(message);
    }

}

