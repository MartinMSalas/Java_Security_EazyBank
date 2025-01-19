package com.mmstechnology.controller;

import com.mmstechnology.model.Loan;
import com.mmstechnology.repository.LoanRepository;
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
        List<Loan> loans = loanRepository.findByCustomerCustomerIdOrderByStartDtDesc(id);

        return loans;
    }

}
