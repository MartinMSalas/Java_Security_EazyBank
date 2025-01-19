package com.mmstechnology.repository;

import com.mmstechnology.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card,String> {

    List<Card> findByCustomerCustomerId(int customerId);

}
