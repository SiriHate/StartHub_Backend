package org.siri_hate.notification_service.model.mails.confirmation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.siri_hate.notification_service.model.enums.EmailSubject;
import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * This class represents a registration confirmation mail.
 * It extends the MailTemplate class and includes additional fields such as fullName and confirmationUrl.
 * It uses the @Data and @EqualsAndHashCode(callSuper = true) annotations from Lombok to generate getter, setter, equals, and hashCode methods.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RegistrationConfirmationMail extends MailTemplate {

    /**
     * The full name of the recipient.
     */
    private String fullName;

    /**
     * The URL for registration confirmation.
     */
    private final String confirmationUrl;

    /**
     * The base URL for confirmation, retrieved from the environment variables.
     */
    private String confirmationBaseUrl = System.getenv("CONFIRMATION_BASE_URL");

    /**
     * The URL for registration confirmation, retrieved from the environment variables.
     */
    private String confirmRegistrationUrl = System.getenv("CONFIRMATION_REGISTRATION_URL");

    /**
     * Constructor for the RegistrationConfirmationMail class.
     * It takes the recipient's email address, full name, and a token as parameters.
     * It sets the subject to REGISTRATION_CONFIRMATION and constructs the confirmationUrl.
     *
     * @param toEmailAddress the recipient's email address
     * @param fullName the recipient's full name
     * @param token the token for registration confirmation
     */
    public RegistrationConfirmationMail(String toEmailAddress, String fullName, String token) {
        this.toEmailAddress = toEmailAddress;
        this.fullName = fullName;
        this.subject = EmailSubject.REGISTRATION_CONFIRMATION.getSubject();
        this.confirmationUrl = confirmationBaseUrl + confirmRegistrationUrl + token;
    }

    /**
     * This method prepares the message to be sent.
     * It sets the fullName and confirmationUrl variables in the context and processes the "registration_confirmation_mail_template" template.
     *
     * @param context the context in which the template will be processed
     * @param templateEngine the template engine to process the template
     */
    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("fullName", fullName);
        context.setVariable("confirmationUrl", this.confirmationUrl);
        this.message = templateEngine.process("registration_confirmation_mail_template", context);
    }

}