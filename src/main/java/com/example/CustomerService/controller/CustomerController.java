package com.example.CustomerService.controller;

import com.example.CustomerService.dto.Response;
import com.example.CustomerService.model.Customer;
import com.example.CustomerService.service.CustomerService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer-service/food-delivery")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Response> addCustomer(@Valid @RequestBody Customer customer) {
        Response response = customerService.addCustomer(customer);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable int customerId) {
        Response response = customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Response> updateCustomer(@Valid @RequestBody Customer customer) {
        Response response = customerService.updateCustomer(customer);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/viewAllCustomers")
    public ResponseEntity<Response> viewAllCustomers() {
        Response response = customerService.viewAllCustomers();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    int retryCount = 1;

    @GetMapping("/viewCustomerById/{customerId}")
//    @CircuitBreaker(name = "ratingRestaurantBreaker", fallbackMethod = "ratingRestaurantFallback")
//    @Retry(name = "ratingRestaurantService", fallbackMethod = "ratingRestaurantFallback")
//    @RateLimiter(name = "customerRateLimiter", fallbackMethod = "ratingRestaurantFallback")
    public ResponseEntity<Response> findCustomerById(@PathVariable int customerId) {
     /*   log.info("Retry count: {}", retryCount);
        retryCount++;*/
        Response response = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> ratingRestaurantFallback(int customerId, Exception ex) {
        log.info("Fallback is executed because service is down" + ex.getMessage());
        Response response = new Response();
        response.setSuccess(false);
        response.getErrMessage().add("Fallback is executed because service is down");
        response.setResponseData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

