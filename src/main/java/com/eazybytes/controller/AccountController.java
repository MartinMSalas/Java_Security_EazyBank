package com.eazybytes.controller;

import com.eazybytes.model.Account;
import com.eazybytes.repository.AccountRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        return accountRepository.findByCustomerId(id).orElseThrow(() -> new AccountNotFoundException("Account with id: " + id + " not found"));
    }

}
