package org.usra.creditApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usra.creditApp.model.CustomerPaymentDetails;

@Repository
public interface CustomerPaymentsRepository extends JpaRepository<CustomerPaymentDetails, Integer> {

}
