package org.siri_hate.chat_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * This is the main entry point for the Chat Service application.
 * It uses Spring Boot and AspectJ for its operation.
 *
 * @author SiriHate
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ChatServiceApplication {

    /**
     * The main method which serves as the entry point for the application.
     * It starts the Spring Boot application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApplication.class, args);
    }

}
