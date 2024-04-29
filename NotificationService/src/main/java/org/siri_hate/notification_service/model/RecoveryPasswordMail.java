package org.siri_hate.notification_service.model;

import lombok.Data;

@Data
public class RecoveryPasswordMail {

    private String toEmailAddress;

    private String subject = "Восстановление пароля на платформе StartHub";

    private String message;

    public RecoveryPasswordMail(String toEmailAddress, int code) {
        this.toEmailAddress = toEmailAddress;
        this.message =
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Восстановление пароля StartHub</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px;\">\n" +
                "        <h2>Восстановление пароля StartHub</h2>\n" +
                "        <p>Вы запросили сброс пароля на платформе развития стартапов StartHub. Для завершения процедуры восстановления, пожалуйста, введите следующий 5-значный код:</p>\n" +
                "        <p style=\"font-size: 24px; font-weight: bold; text-align: center;\">" + code + "</p>\n" +
                "        <p>Если вы не запрашивали сброс пароля, пожалуйста, проигнорируйте это сообщение.</p>\n" +
                "        <p>С уважением,<br>Команда StartHub</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

}
