package com.cognizant.loanservice.controller;

import com.cognizant.loanservice.model.Loan;
import com.cognizant.loanservice.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST endpoints for loans. */
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // GET http://localhost:8082/loans
    @GetMapping
    public List<Loan> getAll() {
        return loanService.getAllLoans();
    }

    // GET http://localhost:8082/loans/L2001
    @GetMapping("/{number}")
    public ResponseEntity<Loan> getByNumber(@PathVariable String number) {
        return loanService.getLoanByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
