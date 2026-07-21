package com.cognizant.accountservice.controller;

import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST endpoints for accounts. */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // GET http://localhost:8081/accounts
    @GetMapping
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

    // GET http://localhost:8081/accounts/A1001
    @GetMapping("/{number}")
    public ResponseEntity<Account> getByNumber(@PathVariable String number) {
        return accountService.getAccountByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
