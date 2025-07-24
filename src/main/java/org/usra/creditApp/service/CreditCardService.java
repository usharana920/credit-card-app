package org.usra.creditApp.service;


import org.usra.creditApp.dto.CustomerDetailsUpdateRequest;
import org.usra.creditApp.dto.CustomerSignUpRequest;
import org.usra.creditApp.dto.CustomerStatusRequest;

public interface CreditCardService {

    String processRegistration(CustomerSignUpRequest customerSignUpRequest);

    String updateCard(String customerId, CustomerStatusRequest customerStatusRequest);

    String updateCustomerProfile(String customerId, CustomerDetailsUpdateRequest customerStatusRequest);
}
