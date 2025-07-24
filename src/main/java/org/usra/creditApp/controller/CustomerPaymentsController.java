package org.usra.creditApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.usra.creditApp.dto.CustomerPaymentRequest;
import org.usra.creditApp.service.CustomerPaymentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class CustomerPaymentsController {

    private final CustomerPaymentService customerPaymentService;

    @PostMapping(value = "/pay/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String processPayment(@PathVariable(name = "customerId") String customerId, @RequestBody CustomerPaymentRequest customerPaymentRequest){
        String message = customerPaymentService.processPayment(customerId, customerPaymentRequest);
        return message;
    }

}
