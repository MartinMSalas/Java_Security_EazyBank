package com.eazybytes.repository;

import com.eazybytes.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card,String> {

    List<Card> findByCustomerId(int customerId);

}