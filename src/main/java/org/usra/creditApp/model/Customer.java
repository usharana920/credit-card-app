package org.usra.creditApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.springframework.data.annotation.LastModifiedDate;
import org.usra.creditApp.enums.CreditCardStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private int customerId;

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
    @Enumerated(EnumType.STRING)
    private CreditCardStatus cardStatus;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
