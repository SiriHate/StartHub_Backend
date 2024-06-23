package org.siri_hate.notification_service.model.mails;

import lombok.Data;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * This is an abstract class that represents a mail template.
 * It includes fields such as toEmailAddress, subject, and message.
 * It uses the @Data annotation from Lombok to generate getter and setter methods.
 * It also includes an abstract method prepareMessage that needs to be implemented by any class that extends this class.
 */
@Data
public abstract class MailTemplate {

    /**
     * The email address to which the mail will be sent.
     */
    protected String toEmailAddress;

    /**
     * The subject of the mail.
     */
    protected String subject;

    /**
     * The message content of the mail.
     */
    protected String message;

    /**
     * This is an abstract method that prepares the message to be sent.
     * It takes a Context object and a TemplateEngine object as parameters.
     * The implementation of this method should set the necessary variables in the context and process the appropriate template.
     *
     * @param context the context in which the template will be processed
     * @param templateEngine the template engine to process the template
     */
    public abstract void prepareMessage(Context context, TemplateEngine templateEngine);

}