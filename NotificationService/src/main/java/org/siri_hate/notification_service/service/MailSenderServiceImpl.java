package org.siri_hate.notification_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.siri_hate.notification_service.exception.MailSendException;
import org.siri_hate.notification_service.model.DeletedAccountMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailSenderServiceImpl {

    final private JavaMailSender mailSender;

    @Autowired
    private MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmailAddress;

    public void sendSuccessfulRegistrationMail(String ToEmailAddress, String fullName) throws MessagingException {
        DeletedAccountMail deletedAccountMail = new DeletedAccountMail(ToEmailAddress, fullName);
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            mail.setSubject(deletedAccountMail.getSubject());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mail, true);
            helper.setFrom(fromEmailAddress);
            helper.setTo(ToEmailAddress);
            org.thymeleaf.context.Context context = new Context();
            context.setVariable("fullName", fullName);
            String html = templateEngine.process("DeletedAccountMailTemplate", context);
            helper.setText(html, true);
            mailSender.send(mail);
        } catch (MailSendException e) {
            throw new MailSendException(e.getMessage());
        }
    }

}
