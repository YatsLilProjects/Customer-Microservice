package com.example.CustomerService.service;

import com.example.CustomerService.dto.Response;
import com.example.CustomerService.model.Customer;

public interface CustomerService {
    Response addCustomer(Customer customer);

    Response deleteCustomerById(int customerId);

    Response updateCustomer(Customer customer);

    Response viewAllCustomers();

    Response getCustomerById(int customerId);


}
