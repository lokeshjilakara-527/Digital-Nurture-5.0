package com.cognizant.loanservice.service;

import com.cognizant.loanservice.model.Loan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Business logic + in-memory data (no database needed). */
@Service
public class LoanService {

    private final List<Loan> loans = List.of(
        new Loan("L2001", "HOME", 2500000.0, 8.5, "Alger Wilson"),
        new Loan("L2002", "CAR", 750000.0, 9.2, "Klein Moretti"),
        new Loan("L2003", "PERSONAL", 300000.0, 12.0, "Audrey Hall")
    );

    public List<Loan> getAllLoans() {
        return loans;
    }

    public Optional<Loan> getLoanByNumber(String number) {
        return loans.stream()
                .filter(l -> l.getNumber().equalsIgnoreCase(number))
                .findFirst();
    }
}
