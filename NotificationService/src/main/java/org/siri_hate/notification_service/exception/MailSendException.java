package org.siri_hate.notification_service.exception;

/**
 * This class represents a custom exception that is thrown when an error occurs while sending an email.
 * It extends the RuntimeException class, meaning it is an unchecked exception.
 */
public class MailSendException extends RuntimeException {

    /**
     * Constructor for the MailSendException class.
     * It takes a message as a parameter and passes it to the superclass constructor.
     *
     * @param message the detail message associated with the exception
     */
    public MailSendException(String message) {
        super(message);
    }

}