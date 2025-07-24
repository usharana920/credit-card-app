package org.usra.creditApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.usra.creditApp.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c from Customer c WHERE c.cId = :customerId")
    Optional<Customer> findByCustomerId(String customerId);
}
