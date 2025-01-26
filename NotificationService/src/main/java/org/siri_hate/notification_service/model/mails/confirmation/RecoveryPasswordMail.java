package org.siri_hate.notification_service.model.mails.confirmation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.siri_hate.notification_service.model.enums.EmailSubject;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Data
@EqualsAndHashCode(callSuper = true)
public class RecoveryPasswordMail extends MailTemplate {


    private String fullName;


    private String confirmationUrl;


    private final String confirmationBaseUrl = System.getenv("CONFIRMATION_BASE_URL");


    private final String confirmPasswordRecoveryUrl = System.getenv("CONFIRMATION_PASSWORD_RECOVERY_URL");


    public RecoveryPasswordMail(String toEmailAddress, String fullName, String token) {
        this.toEmailAddress = toEmailAddress;
        this.fullName = fullName;
        this.subject = EmailSubject.CHANGE_PASSWORD_CONFIRMATION.getSubject();
        this.confirmationUrl = confirmationBaseUrl + confirmPasswordRecoveryUrl + token;
    }


    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("fullName", fullName);
        context.setVariable("confirmationUrl", this.confirmationUrl);
        this.message = templateEngine.process("recovery_password_mail_template", context);
    }

}