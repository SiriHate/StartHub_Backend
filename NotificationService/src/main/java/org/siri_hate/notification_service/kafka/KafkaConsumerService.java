package org.siri_hate.notification_service.kafka;

import jakarta.mail.MessagingException;

/**
 * This interface defines the contract for a service that consumes messages from Kafka topics.
 * It declares methods for listening to the Notification and Confirmation topics.
 * Each method takes a message as a parameter and throws a MessagingException if an error occurs while processing the message.
 */
public interface KafkaConsumerService {

   /**
    * This method is a listener for the Notification topic.
    * It takes a message as a parameter and processes it.
    * It throws a MessagingException if an error occurs while processing the message.
    *
    * @param message the message from the Notification topic
    * @throws MessagingException if an error occurs while processing the message
    */
   void NotificationTopicListener(String message) throws MessagingException;

   /**
    * This method is a listener for the Confirmation topic.
    * It takes a message as a parameter and processes it.
    * It throws a MessagingException if an error occurs while processing the message.
    *
    * @param message the message from the Confirmation topic
    * @throws MessagingException if an error occurs while processing the message
    */
   void ConfirmationTopicListener(String message) throws MessagingException;

}