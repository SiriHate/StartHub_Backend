package org.siri_hate.notification_service.model.mails;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
public class RegistrationConfirmationMail {
    private String toEmailAddress;
    private String subject = "Проверочный код для подтверждения регистрации на платформе StartHub";
    private String message;
    private String confirmationUrl;

    @Value("${confirmation.base.url}")
    private String confirmationBaseUrl;

    public RegistrationConfirmationMail(String toEmailAddress, String token, String confirmationBaseUrl) {
        this.toEmailAddress = toEmailAddress;
        this.confirmationBaseUrl = confirmationBaseUrl;
        Context context = new Context();
        confirmationUrl = confirmationBaseUrl + "/confirmRegistration?token=" + token;
        context.setVariable("confirmationUrl", confirmationUrl);
        TemplateEngine templateEngine = new TemplateEngine();
        this.message = templateEngine.process(
                "src/main/resources/templates/mail-templates/RegistrationConfirmationMailTemplate",
                context
        );
    }

}
