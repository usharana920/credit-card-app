package org.usra.creditApp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    private final CustomerPaymentsRepository customerPaymentsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public String processPayment(String customerId, CustomerPaymentRequest customerPaymentRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        if (optionalCustomer.isEmpty()){
            throw CreditCoreException.asBudgetException(ExceptionCodes.CUSTOMER_NOT_FOUND);
        }
        Customer orgCustomer = optionalCustomer.get();
        // pay and update. should be transactional.
        payAndUpdate(orgCustomer, customerPaymentRequest);
        return "Done";
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
