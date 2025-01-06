package com.eazybytes.repository;

import com.eazybytes.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer> {

    List<Loan> findByCustomerCustomerIdOrderByStartDtDesc(int customerId);
}
