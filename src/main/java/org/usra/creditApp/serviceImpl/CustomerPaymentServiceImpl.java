package org.usra.creditApp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usra.creditApp.dto.CustomerPaymentRequest;
import org.usra.creditApp.enums.ExceptionCodes;
import org.usra.creditApp.exception.CreditCoreException;
import org.usra.creditApp.model.Customer;
import org.usra.creditApp.model.CustomerPaymentDetails;
import org.usra.creditApp.repository.CustomerPaymentsRepository;
import org.usra.creditApp.repository.CustomerRepository;
import org.usra.creditApp.service.CustomerPaymentService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    private final CustomerPaymentsRepository customerPaymentsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public String processPayment(String customerId, CustomerPaymentRequest customerPaymentRequest) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
            if (optionalCustomer.isEmpty()) {
                throw CreditCoreException.asBudgetException(ExceptionCodes.CUSTOMER_NOT_FOUND);
            }
            Customer orgCustomer = optionalCustomer.get();
            // pay and update. should be transactional.
            payAndUpdate(orgCustomer, customerPaymentRequest);
            return "Done";
        }catch (CreditCoreException e) {
            log.error("Error updating card for customerId: {}", customerId);
            return "Failed to update card. Customer not present. ";
        } catch (Exception e) {
            log.error("Unexpected error updating card for customerId {}: {}", customerId, e.getMessage());
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    @Transactional
    public void payAndUpdate(Customer orgCustomer, CustomerPaymentRequest customerPaymentRequest){
        double balance = orgCustomer.getBalance();
        CustomerPaymentDetails customerPaymentDetails = new CustomerPaymentDetails();
        customerPaymentDetails.setCustomer(orgCustomer);
        customerPaymentDetails.setPaymentAmount(customerPaymentRequest.getPaymentAmount());
        customerPaymentDetails.setPaymentSource(customerPaymentRequest.getPaymentSource());
        customerPaymentsRepository.save(customerPaymentDetails);

        balance -= customerPaymentRequest.getPaymentAmount();
        orgCustomer.setBalance(balance);
        customerRepository.save(orgCustomer);
    }
}
