package com.mmstechnology.controller;

import com.mmstechnology.model.AccountTransaction;
import com.mmstechnology.repository.AccountTransactionRepository;
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
                .findByCustomerCustomerIdOrderByTransactionDtDesc(id);

        return accountTransactions;
    }

}
