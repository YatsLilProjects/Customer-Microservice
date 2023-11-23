package com.example.CustomerService.model;


import com.example.CustomerService.dto.Rating;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_generator")
    @SequenceGenerator(
            name = "customer_generator",
            sequenceName = "customer_sequence_name",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z]{2,10}$", message = "Invalid customer name, should be 2 to 10 characters and contain only letters")
    private String customerName;

    @Column(name = "customer_contactNo", nullable = false)
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be a 10-digit number")
    private String customerContactNo;

    @Email(message = "Email id should be valid")
    @Column(name = "customer_emailId")
    private String customerEmailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "customer_address", nullable = false)
    @Valid
    private CustomerAddress customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private SignUp signUp;

    @Transient
    private List<Rating> ratings;

}

   /*
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private FoodCart foodCart;*/
