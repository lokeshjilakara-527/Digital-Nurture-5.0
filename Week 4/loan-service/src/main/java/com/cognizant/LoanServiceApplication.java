package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Loan microservice (port 8082). Everything for this service is in this one file. */
@SpringBootApplication
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}

record Loan(String number, String type, double amount, double interestRate, String customerName) { }

@RestController
@RequestMapping("/loans")
class LoanController {

    private final List<Loan> loans = List.of(
        new Loan("L2001", "HOME", 2500000.0, 8.5, "Alger Wilson"),
        new Loan("L2002", "CAR", 750000.0, 9.2, "Klein Moretti"),
        new Loan("L2003", "PERSONAL", 300000.0, 12.0, "Audrey Hall")
    );

    @GetMapping                                   // GET /loans
    public List<Loan> getAll() {
        return loans;
    }

    @GetMapping("/{number}")                       // GET /loans/L2001
    public ResponseEntity<Loan> getByNumber(@PathVariable String number) {
        return loans.stream()
                .filter(l -> l.number().equalsIgnoreCase(number))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
