package com.mmstechnology.repository;

import com.mmstechnology.model.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction,String> {

    List<AccountTransaction> findByCustomerCustomerIdOrderByTransactionDtDesc(int customerId);

}
