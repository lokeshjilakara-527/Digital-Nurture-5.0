package com.cognizant.loanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Loan microservice. Registers itself with Eureka on startup (port 8082). */
@SpringBootApplication
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
