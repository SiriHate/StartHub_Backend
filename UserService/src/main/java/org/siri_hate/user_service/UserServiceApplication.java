package org.siri_hate.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This is the main entry point for the User Service application.
 * It uses Spring Boot and AspectJ for its operation.
 *
 * @author SiriHate
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class UserServiceApplication {

	/**
	 * The main method which serves as the entry point for the application.
	 * It starts the Spring Boot application.
	 *
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
