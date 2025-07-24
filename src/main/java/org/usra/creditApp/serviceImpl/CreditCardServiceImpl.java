package org.usra.creditApp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.usra.creditApp.dto.CustomerDetailsUpdateRequest;
import org.usra.creditApp.dto.CustomerSignUpRequest;
import org.usra.creditApp.dto.CustomerStatusRequest;
import org.usra.creditApp.dto.EmailRequest;
import org.usra.creditApp.enums.CreditCardStatus;
import org.usra.creditApp.enums.ExceptionCodes;
import org.usra.creditApp.exception.CreditCoreException;
import org.usra.creditApp.model.Customer;
import org.usra.creditApp.repository.CustomerRepository;
import org.usra.creditApp.service.CreditCardService;
import org.usra.creditApp.service.EmailService;

import java.util.Optional;

import static org.usra.creditApp.constants.Constants.CREDIT_CARD_APPLICATION;
import static org.usra.creditApp.constants.Constants.CREDIT_CARD_REGISTRATION_RECEIVED_EMAIL_BODY;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    private void saveCustomer(CustomerSignUpRequest customerSignUpRequest) {
        Customer customer = modelMapper.map(customerSignUpRequest, Customer.class);
        customer.setCardStatus(CreditCardStatus.CARD_REQUEST_RECEIVED);
        log.debug("Customer entity is: {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public String processRegistration(CustomerSignUpRequest customerSignUpRequest) {
        saveCustomer(customerSignUpRequest);
        emailService.processEmail(constructEmailRequest(customerSignUpRequest));
        return "Registration process completed.";
    }

    @Override
    public String updateCard(String customerId, CustomerStatusRequest customerStatusRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        if (optionalCustomer.isEmpty()) {
            throw CreditCoreException.asBudgetException(ExceptionCodes.CUSTOMER_NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();
        customer.setCardStatus(CreditCardStatus.fromValue(customerStatusRequest.getCardStatus()));
        customerRepository.save(customer);
        log.debug("Card Status updated for customer: {}", customerId);
        return "Success";
    }

    @Override
    @Transactional
    public String updateCustomerProfile(String customerId, CustomerDetailsUpdateRequest customerDetailsUpdateRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
        if (optionalCustomer.isEmpty()) {
            throw CreditCoreException.asBudgetException(ExceptionCodes.CUSTOMER_NOT_FOUND);
        }
        Customer orgCustomer = optionalCustomer.get();
        Customer updatedCustomer = updateCustomer(customerDetailsUpdateRequest, orgCustomer);
        customerRepository.save(updatedCustomer);
        log.debug("Customer profile updated for customer: {} and new customer profile: {}", customerId, updatedCustomer);
        return "Success";
    }

    private Customer updateCustomer(CustomerDetailsUpdateRequest customerDetailsUpdateRequest, Customer orgCustomer){
        if (customerDetailsUpdateRequest.getFirstName() != null) orgCustomer.setFirstName(customerDetailsUpdateRequest.getFirstName());
        if (customerDetailsUpdateRequest.getLastName() != null) orgCustomer.setLastName(customerDetailsUpdateRequest.getLastName());
        if (customerDetailsUpdateRequest.getEmail() != null) orgCustomer.setEmail(customerDetailsUpdateRequest.getEmail());
        if (customerDetailsUpdateRequest.getUserName() != null) orgCustomer.setUserName(customerDetailsUpdateRequest.getUserName());
        if (customerDetailsUpdateRequest.getPassword() != null) orgCustomer.setPassword(customerDetailsUpdateRequest.getPassword());
        if (customerDetailsUpdateRequest.getOccupation() != null) orgCustomer.setOccupation(customerDetailsUpdateRequest.getOccupation());
        if (customerDetailsUpdateRequest.getSalary() != null) orgCustomer.setSalary(customerDetailsUpdateRequest.getSalary());
        if (customerDetailsUpdateRequest.getDateOfBirth() != null) orgCustomer.setDateOfBirth(customerDetailsUpdateRequest.getDateOfBirth());
        if (customerDetailsUpdateRequest.getSocialSecurityNumber() != null) orgCustomer.setSocialSecurityNumber(customerDetailsUpdateRequest.getSocialSecurityNumber());
        return orgCustomer;
    }

    private EmailRequest constructEmailRequest(CustomerSignUpRequest customerSignUpRequest){
        return EmailRequest.builder().receiverEmail(customerSignUpRequest.getEmail()).emailPayload(CREDIT_CARD_REGISTRATION_RECEIVED_EMAIL_BODY).subject(CREDIT_CARD_APPLICATION)
            .build();
    }

}
