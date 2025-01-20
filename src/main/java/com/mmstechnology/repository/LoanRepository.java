package com.mmstechnology.repository;

import com.mmstechnology.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer> {

    @PreAuthorize("hasRole('USER')")
    List<Loan> findByCustomerCustomerIdOrderByStartDtDesc(int customerId);
}
