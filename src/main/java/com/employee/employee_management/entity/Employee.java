package com.employee.employee_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(nullable = false)
    @NotNull(message = "FirstName can't be null")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "LastName can't be null")
    private String lastName;

    @Email
    @Size(min = 4, max = 25, message = "Please check the size")
    @Column(nullable = false, unique = true)
    private String emailId;


}
