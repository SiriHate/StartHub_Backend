package org.siri_hate.notification_service.service;

import jakarta.mail.MessagingException;
import org.siri_hate.notification_service.model.mails.MailTemplate;

/**
 * This interface defines the contract for a mail sender service.
 * It includes methods for sending confirmation mails, notification mails, and generic emails.
 *
 * @author SiriHate
 */
public interface MailSenderService {

    /**
     * Sends a confirmation mail with the provided message.
     *
     * @param message The message to be included in the confirmation mail.
     * @throws MessagingException If there is an error while sending the mail.
     */
    void sendConfirmationMail(String message) throws MessagingException;

    /**
     * Sends a notification mail with the provided message.
     *
     * @param message The message to be included in the notification mail.
     * @throws MessagingException If there is an error while sending the mail.
     */
    void sendNotificationMail(String message) throws MessagingException;

    /**
     * Sends a generic email based on the provided mail template.
     *
     * @param mailTemplate The template to be used for the email.
     * @throws MessagingException If there is an error while sending the mail.
     */
    void sendEmail(MailTemplate mailTemplate) throws MessagingException;

}