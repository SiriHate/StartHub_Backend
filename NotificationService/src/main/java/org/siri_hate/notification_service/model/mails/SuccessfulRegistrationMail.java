package org.siri_hate.notification_service.model.mails;

import lombok.Data;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
public class SuccessfulRegistrationMail {

    private String toEmailAddress;

    private String subject = "Добро пожаловать на StartHub!";

    private String message;
    
    public SuccessfulRegistrationMail(String toEmailAddress, String name) {
        this.toEmailAddress = toEmailAddress;
        Context context = new Context();
        context.setVariable("name", name);
        TemplateEngine templateEngine = new TemplateEngine();
        this.message = templateEngine.process(
                "src/main/resources/templates/mail-templates/SuccessfulRegistrationMail",
                context
        );
    }

}
