package org.siri_hate.notification_service.model;

import lombok.Data;

@Data
public class RegistrationConfirmationMail {

    private String toEmailAddress;

    private String subject = "Проверочный код для подтверждения регистрации на платформе StartHub";

    private String message;


    public RegistrationConfirmationMail(String toEmailAddress, int code) {
        this.toEmailAddress = toEmailAddress;
        this.message = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Подтверждение регистрации на StartHub</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px;\">\n" +
                "        <h2>Подтверждение регистрации на StartHub</h2>\n" +
                "        <p>Спасибо за регистрацию на платформе развития стартапов StartHub. Для завершения регистрации, пожалуйста, введите следующий код активации:</p>\n" +
                "        <p style=\"font-size: 24px; font-weight: bold; text-align: center;\">" + code + "</p>\n" +
                "        <p>Если вы не регистрировались на нашей платформе, пожалуйста, проигнорируйте это сообщение.</p>\n" +
                "        <p>С уважением,<br>Команда StartHub</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

}
