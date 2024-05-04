package org.siri_hate.notification_service.service;

import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.siri_hate.notification_service.exception.UnknownEmailTypeException;
import org.siri_hate.notification_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.notification_service.kafka.messages.NotificationMessage;
import org.siri_hate.notification_service.model.mails.confirmation.RecoveryPasswordMail;
import org.siri_hate.notification_service.model.mails.confirmation.RegistrationConfirmationMail;
import org.siri_hate.notification_service.model.mails.notification.DeletedAccountMail;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.siri_hate.notification_service.model.mails.notification.PasswordChangeMail;
import org.siri_hate.notification_service.model.mails.notification.SuccessfulRegistrationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    final private JavaMailSender mailSender;

    final private SpringTemplateEngine templateEngine;

    final private Gson gson;

    @Autowired
    private MailSenderServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine, Gson gson) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    @Value("${spring.mail.username}")
    private String fromEmailAddress;

    public void sendConfirmationMail(String message) throws MessagingException {

        ConfirmationMessage confirmationMessage = gson.fromJson(message, ConfirmationMessage.class);

        MailTemplate mailTemplate = switch (confirmationMessage.getMessageType()) {
            case REGISTRATION_CONFIRMATION -> new RegistrationConfirmationMail(
                    confirmationMessage.getUserEmail(),
                    confirmationMessage.getUserFullName(),
                    confirmationMessage.getUserConfirmationToken()
            );
            case CHANGE_PASSWORD_CONFIRMATION -> new RecoveryPasswordMail(
                    confirmationMessage.getUserEmail(),
                    confirmationMessage.getUserFullName(),
                    confirmationMessage.getUserConfirmationToken()
            );
        };

        sendEmail(mailTemplate);
    }

    public void sendNotificationMail(String message) throws MessagingException {

        NotificationMessage notificationMessage = gson.fromJson(message, NotificationMessage.class);

        MailTemplate mailTemplate = switch (notificationMessage.getMessageType()) {
            case SUCCESSFUL_REGISTRATION_NOTIFICATION -> new SuccessfulRegistrationMail(
                    notificationMessage.getUserEmail(),
                    notificationMessage.getUserFullName()
            );
            case DELETED_ACCOUNT_NOTIFICATION -> new DeletedAccountMail(
                    notificationMessage.getUserEmail(),
                    notificationMessage.getUserFullName()
            );
            case CHANGED_PASSWORD_NOTIFICATION -> new PasswordChangeMail(
                    notificationMessage.getUserEmail(),
                    notificationMessage.getUserFullName()
            );
        };

        sendEmail(mailTemplate);
    }

    public void sendEmail(MailTemplate mailTemplate) throws MessagingException {

        Context context = new Context();
        mailTemplate.prepareMessage(context, templateEngine);

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom(fromEmailAddress);
        helper.setTo(mailTemplate.getToEmailAddress());
        helper.setSubject(mailTemplate.getSubject());
        helper.setText(mailTemplate.getMessage(), true);
        FileSystemResource resource = new FileSystemResource("src/main/resources/image/logo.png");
        helper.addInline("logo", resource);

        mailSender.send(mail);
    }

}
