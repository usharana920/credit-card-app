package org.usra.creditApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerSignUpRequest {
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
    @CreationTimestamp
    private LocalDateTime timestamp;
}
