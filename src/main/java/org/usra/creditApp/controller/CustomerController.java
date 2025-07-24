package org.usra.creditApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.usra.creditApp.dto.CustomerDetailsUpdateRequest;
import org.usra.creditApp.dto.CustomerSignUpRequest;
import org.usra.creditApp.dto.CustomerStatusRequest;
import org.usra.creditApp.response.CreditCardRegistrationResponse;
import org.usra.creditApp.service.CreditCardService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CreditCardService creditCardService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardRegistrationResponse register(@RequestBody CustomerSignUpRequest customerSignUpRequest){
        String message = creditCardService.processRegistration(customerSignUpRequest);
        return CreditCardRegistrationResponse.builder().message(message).build();
    }

    @PutMapping(value = "/update-card-status/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardRegistrationResponse updateCardStatus(@PathVariable(name = "customerId") String customerId, @RequestBody CustomerStatusRequest customerStatusRequest){
        String message = creditCardService.updateCard(customerId, customerStatusRequest);
        return CreditCardRegistrationResponse.builder().message(message).build();
    }

    @PutMapping(value = "/update-customer-profile/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditCardRegistrationResponse updateCustomerProfile(@PathVariable(name = "customerId") String customerId, @RequestBody CustomerDetailsUpdateRequest customerStatusRequest){
        String message = creditCardService.updateCustomerProfile(customerId, customerStatusRequest);
        return CreditCardRegistrationResponse.builder().message(message).build();
    }
}
