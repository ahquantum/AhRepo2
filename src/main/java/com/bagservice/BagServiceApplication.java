package com.bagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Main application class for the Bag Service microservice
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Bag Service API",
        version = "1.0",
        description = "API for booking pickup/dropoff, tracking, and insuring bags"
    )
)
public class BagServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BagServiceApplication.class, args);
    }
} 