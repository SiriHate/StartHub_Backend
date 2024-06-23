package org.siri_hate.notification_service.model.mails.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.siri_hate.notification_service.model.enums.EmailSubject;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * This class represents a successful registration mail.
 * It extends the MailTemplate class and includes an additional field, fullName.
 * It uses the @Data and @EqualsAndHashCode(callSuper = true) annotations from Lombok to generate getter, setter, equals, and hashCode methods.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessfulRegistrationMail extends MailTemplate {

    /**
     * The full name of the recipient.
     */
    private String fullName;

    /**
     * Constructor for the SuccessfulRegistrationMail class.
     * It takes the recipient's email address and full name as parameters.
     * It sets the subject to SUCCESSFUL_REGISTRATION_NOTIFICATION.
     *
     * @param toEmailAddress the recipient's email address
     * @param fullName the recipient's full name
     */
    public SuccessfulRegistrationMail(String toEmailAddress, String fullName) {
        this.toEmailAddress = toEmailAddress;
        this.subject = EmailSubject.SUCCESSFUL_REGISTRATION_NOTIFICATION.getSubject();
        this.fullName = fullName;
    }

    /**
     * This method prepares the message to be sent.
     * It sets the fullName variable in the context and processes the "successful_registration_mail_template" template.
     *
     * @param context the context in which the template will be processed
     * @param templateEngine the template engine to process the template
     */
    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("fullName", fullName);
        this.message = templateEngine.process("successful_registration_mail_template", context);
    }

}