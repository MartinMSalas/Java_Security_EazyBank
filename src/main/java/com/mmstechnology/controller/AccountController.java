package com.mmstechnology.controller;

import com.mmstechnology.model.Account;
import com.mmstechnology.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/myAccount")
    public Account getAccountDetails (@RequestParam int id) throws AccountNotFoundException {

        return accountRepository.findByCustomerCustomerId(id).orElseThrow(() -> new AccountNotFoundException("Account with id: " + id + " not found"));
    }

}
