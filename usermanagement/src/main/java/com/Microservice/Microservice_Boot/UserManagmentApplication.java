package com.Microservice.Microservice_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * UserManagmentApplication is the main class that starts the Spring Boot application.
 * It is annotated with @SpringBootApplication, which enables auto-configuration, component scanning,
 * and configuration properties.
 */
@SpringBootApplication
public class UserManagmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserManagmentApplication.class, args);
	}
}
