package com.eazybytes.controller;

import com.eazybytes.model.AccountTransaction;
import com.eazybytes.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public BalanceController(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails (@RequestParam int id) {
        List<AccountTransaction> accountTransactions = accountTransactionRepository
                .findByCustomerIdOrderByTransactionDtDesc(id);

        return accountTransactions;
    }

}
