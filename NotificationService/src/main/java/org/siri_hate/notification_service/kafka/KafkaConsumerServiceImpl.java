package org.siri_hate.notification_service.kafka;

import jakarta.mail.MessagingException;
import org.siri_hate.notification_service.service.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * This class implements the KafkaConsumerService interface and defines the methods for consuming messages from Kafka topics.
 * It uses the @Service annotation to indicate that it is a service class.
 * It uses the @Autowired annotation to inject the MailSenderServiceImpl dependency.
 */
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    /**
     * The service for sending mails.
     */
    private final MailSenderServiceImpl mailSenderService;

    /**
     * Constructor for the KafkaConsumerServiceImpl class.
     * It takes a MailSenderServiceImpl as a parameter and assigns it to the mailSenderService field.
     *
     * @param mailSenderService the service for sending mails
     */
    @Autowired
    public KafkaConsumerServiceImpl(MailSenderServiceImpl mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    /**
     * This method is a listener for the Notification topic.
     * It takes a message as a parameter and sends a notification mail with the message.
     * It throws a MessagingException if an error occurs while sending the mail.
     *
     * @param message the message from the Notification topic
     * @throws MessagingException if an error occurs while sending the mail
     */
    @Override
    @KafkaListener(topics = "notification_topic", groupId = "consumers")
    public void NotificationTopicListener(String message) throws MessagingException {
        mailSenderService.sendNotificationMail(message);
    }

    /**
     * This method is a listener for the Confirmation topic.
     * It takes a message as a parameter and sends a confirmation mail with the message.
     * It throws a MessagingException if an error occurs while sending the mail.
     *
     * @param message the message from the Confirmation topic
     * @throws MessagingException if an error occurs while sending the mail
     */
    @Override
    @KafkaListener(topics = "confirmation_topic", groupId = "consumers")
    public void ConfirmationTopicListener(String message) throws MessagingException {
        mailSenderService.sendConfirmationMail(message);
    }

}