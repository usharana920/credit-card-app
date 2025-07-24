package org.usra.creditApp.service;

import org.usra.creditApp.dto.CustomerPaymentRequest;

public interface CustomerPaymentService {

    String processPayment(String customerId, CustomerPaymentRequest customerPaymentRequest);
}
