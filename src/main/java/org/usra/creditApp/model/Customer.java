package org.usra.creditApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.usra.creditApp.enums.CreditCardStatus;
import org.usra.creditApp.enums.CreditCardType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;

    private String occupation;
    private double salary;
    private LocalDate dateOfBirth;
    private String socialSecurityNumber;
    private double balance = 5000.0;
    @Enumerated(EnumType.STRING)
    private CreditCardStatus cardStatus;

    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;
    @CreationTimestamp
    private LocalDateTime timestamp;
    private LocalDateTime accountOpenedDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerPaymentDetails> payments;

}
