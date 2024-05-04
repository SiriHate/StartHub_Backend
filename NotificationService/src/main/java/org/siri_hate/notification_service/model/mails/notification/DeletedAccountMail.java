package org.siri_hate.notification_service.model.mails.notification;

import lombok.Data;

import lombok.EqualsAndHashCode;
import org.siri_hate.notification_service.model.enums.EmailSubject;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeletedAccountMail extends MailTemplate {

    private String fullName;

    public DeletedAccountMail(String toEmailAddress, String fullName) {
        this.toEmailAddress = toEmailAddress;
        this.subject = EmailSubject.DELETED_ACCOUNT_NOTIFICATION.getSubject();
        this.fullName = fullName;
    }

    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("fullName", fullName);
        this.message = templateEngine.process("deleted_account_mail_template", context);
    }

}

