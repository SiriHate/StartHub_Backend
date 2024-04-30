package org.siri_hate.notification_service.service;

import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.siri_hate.notification_service.exception.MailSendException;
import org.siri_hate.notification_service.model.ConfirmationToken;
import org.siri_hate.notification_service.model.mails.RegistrationConfirmationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class MailSenderServiceImpl {

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

    @Value("${confirmation.base.url}")
    private String confirmationBaseUrl;

    @KafkaListener(topics = "notification_topic", groupId = "consumers")
    public void sendSuccessfulRegistrationMail(String message) throws MessagingException {

        ConfirmationToken confirmationToken = gson.fromJson(message, ConfirmationToken.class);

        String toEmailAddress = confirmationToken.getEmail();
        String token = confirmationToken.getConfirmationToken();

        RegistrationConfirmationMail registrationConfirmationMail = new RegistrationConfirmationMail(
                toEmailAddress,
                token,
                confirmationBaseUrl
        );

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            mail.setSubject(registrationConfirmationMail.getSubject());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mail, true);
            helper.setFrom(fromEmailAddress);
            helper.setTo(toEmailAddress);
            org.thymeleaf.context.Context context = new Context();
            context.setVariable("confirmationUrl", registrationConfirmationMail.getConfirmationUrl());
            String html = templateEngine.process("RegistrationConfirmationMailTemplate", context);
            helper.setText(html, true);
            mailSender.send(mail);
        } catch (MailSendException e) {
            throw new MailSendException(e.getMessage());
        }

    }

}
