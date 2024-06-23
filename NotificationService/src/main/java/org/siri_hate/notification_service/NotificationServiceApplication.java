package org.siri_hate.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This is the main entry point for the Notification Service application.
 * It uses Spring Boot and AspectJ for its operation.
 *
 * @author SiriHate
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching(proxyTargetClass = true)
public class NotificationServiceApplication {

    /**
     * The main method which serves as the entry point for the application.
     * It starts the Spring Boot application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}