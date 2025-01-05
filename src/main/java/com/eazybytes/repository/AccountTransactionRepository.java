package com.eazybytes.repository;

import com.eazybytes.model.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction,String> {

    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
