package org.siri_hate.notification_service.model;

import lombok.Data;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;

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
        this.message = templateEngine.process("deleted-account-mail-template", context);
    }

}
