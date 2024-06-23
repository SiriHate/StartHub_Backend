package org.siri_hate.main_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the Main Service application.
 * It uses Spring Boot and AspectJ for its operation.
 *
 * @author SiriHate
 */
@SpringBootApplication
public class MainServiceApplication {

    /**
     * The main method which serves as the entry point for the application.
     * It starts the Spring Boot application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

}
