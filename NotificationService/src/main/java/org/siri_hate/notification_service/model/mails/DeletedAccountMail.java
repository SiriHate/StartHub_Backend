package org.siri_hate.notification_service.model.mails;

import lombok.Data;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
public class DeletedAccountMail {

    private String toEmailAddress;

    private String subject = "Удаление аккаунта на платформе StartHub";

    private String message;

    public DeletedAccountMail(String toEmailAddress, String fullName) {
        this.toEmailAddress = toEmailAddress;
        Context context = new Context();
        context.setVariable("fullName", fullName);
        TemplateEngine templateEngine = new TemplateEngine();
        this.message = templateEngine.process(
                "src/main/resources/templates/mail-templates/DeletedAccountMailTemplate",
                context
        );
    }

}
