package org.siri_hate.notification_service.model.mails.confirmation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.siri_hate.notification_service.model.enums.EmailSubject;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Data
@EqualsAndHashCode(callSuper = true)
public class RegistrationConfirmationMail extends MailTemplate {


    private String fullName;


    private final String confirmationUrl;


    private String confirmationBaseUrl = System.getenv("CONFIRMATION_BASE_URL");


    private String confirmRegistrationUrl = System.getenv("CONFIRMATION_REGISTRATION_URL");


    public RegistrationConfirmationMail(String toEmailAddress, String fullName, String token) {
        this.toEmailAddress = toEmailAddress;
        this.fullName = fullName;
        this.subject = EmailSubject.REGISTRATION_CONFIRMATION.getSubject();
        this.confirmationUrl = confirmationBaseUrl + confirmRegistrationUrl + token;
    }


    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("fullName", fullName);
        context.setVariable("confirmationUrl", this.confirmationUrl);
        this.message = templateEngine.process("registration_confirmation_mail_template", context);
    }

}