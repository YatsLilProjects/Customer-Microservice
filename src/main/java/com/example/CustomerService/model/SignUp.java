package com.example.CustomerService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SignUp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "signup_generator")
    @SequenceGenerator(
            name = "signup_generator",
            sequenceName = "signup_sequence_name",
            allocationSize = 1
    )
    private Integer signUpId;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{2,10}$"
            , message = "Username must consist of 2 to 10 alphanumeric characters or underscores (a-z, A-Z, 0-9, _)")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
            , message = "Password must meet the following criteria:" +
            "At least 8 characters long.\n" +
            "Contains at least one lowercase letter.\n" +
            "Contains at least one uppercase letter.\n" +
            "Contains at least one digit.\n" +
            "Contains at least one special character (e.g., !, @, #, $, etc.)")
    private String password;

    @OneToOne
    @JsonBackReference
    private Customer customer;
}
