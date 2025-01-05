package com.eazybytes.controller;

import com.eazybytes.model.Loan;
import com.eazybytes.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @GetMapping("/myLoans")
    public List<Loan> getLoansDetails (@RequestParam int id) {
        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);

        return loans;
    }

}
