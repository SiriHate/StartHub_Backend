package org.siri_hate.notification_service.model.mails;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
public class RecoveryPasswordMail {

    private String toEmailAddress;

    private String subject = "Восстановление пароля на платформе StartHub";

    private String message;

    @Value("${confirmation.url}")
    private String ConfirmationUrl;

    public RecoveryPasswordMail(String toEmailAddress, String token) {
        this.toEmailAddress = toEmailAddress;
        Context context = new Context();
        String confirmationUrl = ConfirmationUrl + "?token=" + token;
        context.setVariable("confirmationUrl", "https://ya.ru");
        TemplateEngine templateEngine = new TemplateEngine();
        this.message = templateEngine.process(
                "src/main/resources/templates/mail-templates/RecoveryPasswordMailTemplate",
                context
        );
    }

}
