package com.cognizant.accountservice.service;

import com.cognizant.accountservice.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic + in-memory data store.
 * (Kept in-memory on purpose so the microservice runs with NO database setup.)
 */
@Service
public class AccountService {

    private final List<Account> accounts = List.of(
        new Account("A1001", "SAVINGS", 52000.0, "Alger Wilson"),
        new Account("A1002", "CURRENT", 118500.0, "Klein Moretti"),
        new Account("A1003", "SAVINGS", 9800.0, "Audrey Hall")
    );

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public Optional<Account> getAccountByNumber(String number) {
        return accounts.stream()
                .filter(a -> a.getNumber().equalsIgnoreCase(number))
                .findFirst();
    }
}
