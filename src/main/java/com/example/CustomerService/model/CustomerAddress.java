package com.example.CustomerService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerAddress {

    @Column(name = "customer_city", nullable = false)
    @Pattern(regexp = "^[a-zA-Z\s-]+$", message = "Invalid city name")
    private String customerCity;

    @Column(name = "customer_street", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9\s-]+$", message = "Invalid street name")
    private String customerStreet;

    @Pattern(regexp = "^\\d{6}$", message = "PIN code must be a 6 digit number")
    @Column(name = "customer_pinCode")
    private String customerPinCode;
}
