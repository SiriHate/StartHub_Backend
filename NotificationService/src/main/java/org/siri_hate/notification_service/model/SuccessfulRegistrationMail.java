package org.siri_hate.notification_service.model;

import lombok.Data;

@Data
public class SuccessfulRegistrationMail {

    private String toEmailAddress;

    private String subject = "Добро пожаловать на StartHub!";

    private String message;

    public SuccessfulRegistrationMail(String toEmailAddress, String fullName) {
        this.toEmailAddress = toEmailAddress;
        this.message =
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            font-size: 24px;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            max-width: 600px;\n" +
                        "            margin: 0 auto;\n" +
                        "            padding: 20px;\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .blue-box {\n" +
                        "            background-color: #007bff; /* Голубой цвет */\n" +
                        "            color: white; /* Цвет текста белый */\n" +
                        "            padding: 10px; /* Внутренний отступ */\n" +
                        "            display: inline-block; /* Элемент занимает только необходимое пространство */\n" +
                        "            width: 100%; /* Занимает всю доступную ширину */\n" +
                        "        }\n" +
                        "        h3 {\n" +
                        "            font-size: 50px;\n" +
                        "            margin: 0; /* Убираем внешние отступы для заголовка */\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"blue-box\">\n" +
                        "            <h3>Добро пожаловать на StartHub</h3>\n" +
                        "        </div>\n" +
                        "        <p>Уважаемый(ая) [[fullName]],</p>\n" +
                        "        <p>Мы рады приветствовать вас на платформе развития стартапов StartHub. Ваша регистрация прошла успешно!</p>\n" +
                        "        <p>Мы уверены, что вы найдете много интересного на нашей платформе. Если у вас есть какие-либо вопросы или нужна помощь, не стесняйтесь обращаться к нам.</p>\n" +
                        "        <p>С уважением,<br>Команда StartHub</p>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>\n";
    }

}
