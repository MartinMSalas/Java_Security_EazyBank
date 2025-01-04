package com.eazybytes.repository;

import com.eazybytes.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer> {
}
