package com.eazybytes.repository;

import com.eazybytes.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
     Optional<Account> findByCustomerCustomerId(int customerId);
}
