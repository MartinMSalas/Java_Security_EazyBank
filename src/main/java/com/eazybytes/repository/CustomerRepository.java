package com.eazybytes.repository;

import com.eazybytes.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.authorities WHERE c.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);
}
