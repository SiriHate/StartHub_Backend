package org.siri_hate.notification_service.service;

import jakarta.mail.MessagingException;
import org.siri_hate.notification_service.model.mails.MailTemplate;

public interface MailSenderService {

  void sendConfirmationMail(String message) throws MessagingException;

  void sendNotificationMail(String message) throws MessagingException;

  void sendProjectUpdateMail(String message) throws MessagingException;

  void sendEmail(MailTemplate mailTemplate) throws MessagingException;
}
