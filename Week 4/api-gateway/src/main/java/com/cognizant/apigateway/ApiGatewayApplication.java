package com.cognizant.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API Gateway (port 8080). A single front door for all microservices:
 * clients call the gateway, and it forwards to the right service found via Eureka.
 * Routing rules are declared in application.yml.
 */
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
