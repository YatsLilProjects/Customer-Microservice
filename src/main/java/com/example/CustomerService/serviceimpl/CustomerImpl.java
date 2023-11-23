package com.example.CustomerService.serviceimpl;

import com.example.CustomerService.dto.Rating;
import com.example.CustomerService.dto.Response;
import com.example.CustomerService.dto.Restaurant;
import com.example.CustomerService.external.services.RatingService;
import com.example.CustomerService.external.services.RestaurantService;
import com.example.CustomerService.model.Customer;
import com.example.CustomerService.model.UserType;
import com.example.CustomerService.repository.CustomerRepository;
import com.example.CustomerService.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RatingService ratingService;

    @Override
    public Response addCustomer(Customer customer) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            if (customer != null) {
                customer.setUserType(UserType.CUSTOMER);
                Customer newCustomer = customerRepository.save(customer);
                response.setSuccess(true);
                response.setResponseData(newCustomer);
            } else {
                response.getErrMessage().add("Customer Not Inserted");
            }
        } catch (Exception e) {
            response.getErrMessage().add("Customer Not Added");
            log.error("Error in addCustomer {}", e);
        }
        return response;
    }

    @Override
    public Response deleteCustomerById(int customerId) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            boolean customerExists = customerRepository.existsById(customerId);
            if (customerExists) {
                customerRepository.deleteById(customerId);
                response.setSuccess(true);
            } else {
                response.getErrMessage().add("Customer Not Found");
            }
        } catch (Exception e) {
            response.getErrMessage().add("Customer Not Deleted");
            log.error("Error in deleteCustomer {}", e);
        }
        return response;
    }

    @Override
    public Response updateCustomer(Customer customer) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            boolean customerExists = customerRepository.existsById(customer.getCustomerId());
            if (customerExists) {
                Customer updatedCustomer = customerRepository.save(customer);
                response.setSuccess(true);
                response.setResponseData(updatedCustomer);
            } else {
                response.getErrMessage().add("Customer Not Found");
            }
        } catch (Exception e) {
            response.getErrMessage().add("Customer Not Updated");
            log.error("Error in updateCustomer {}", e);
        }
        return response;
    }

    @Override
    public Response viewAllCustomers() {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            List<Customer> customers = customerRepository.findAll();
            List<Customer> filteredCustomers = new ArrayList<>();
            for (Customer customer : customers) {
                UserType userType = customer.getUserType();
                if (UserType.CUSTOMER.equals(userType)) {
                    filteredCustomers.add(customer);
                }
            }
            if (!filteredCustomers.isEmpty()) {
                response.setSuccess(true);
                response.setResponseData(filteredCustomers);
            } else {
                response.getErrMessage().add("No customers");
            }
        } catch (Exception e) {
            log.error("Error in viewAllCustomers {}", e);
        }
        return response;
    }

    @Override
    public Response getCustomerById(int customerId) {
        Response response = new Response(false, new ArrayList<>(), null);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Customer customerData = customer.get();
            response.setSuccess(true);
            List<Rating> ratings = List.of(Objects.requireNonNull(restTemplate.getForObject("http://RATING-SERVICE/rating-service/food-delivery/ratingsByCustomerId/" + customerData.getCustomerId(), Rating[].class)));
            List<Rating> ratingList = ratings.stream().map(rating -> {
                ResponseEntity<Restaurant> restaurantResponseEntity = restTemplate.getForEntity("http://RESTAURANT-SERVICE/restaurant-service/food-delivery/viewRestaurantById/" + rating.getRestaurantId(), Restaurant.class);
                Restaurant restaurant = restaurantResponseEntity.getBody();
                rating.setRestaurant(restaurant);
                return rating;
            }).toList();
            customerData.setRatings(ratingList);
            response.setResponseData(customerData);
        } else {
            response.getErrMessage().add("Customer Not Found");
        }
        return response;
    }


}



   /* @Override
    public Response getCustomerById(int customerId) {
        Response response = new Response(false, new ArrayList<>(), null);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Customer customerData = customer.get();
            response.setSuccess(true);
//                List<Rating> ratings = List.of(Objects.requireNonNull(restTemplate.getForObject("http://RATING-SERVICE/rating-service/food-delivery/ratingsByCustomerId/" + customerData.getCustomerId(), Rating[].class)));
            List<Rating> ratings = ratingService.findRatingsByCustomerId(customerData.getCustomerId());
            List<Rating> ratingList = ratings.stream().map(rating -> {

                *//*    ResponseEntity<Restaurant> restaurantResponseEntity = restTemplate.getForEntity("http://RESTAURANT-SERVICE/restaurant-service/food-delivery/viewRestaurantById/" + rating.getRestaurantId(), Restaurant.class);
                    Restaurant restaurant = restaurantResponseEntity.getBody();*//*

                Restaurant restaurant = restaurantService.getRestaurant(rating.getRestaurantId());
                rating.setRestaurant(restaurant);
                return rating;
            }).toList();
            customerData.setRatings(ratingList);
            response.setResponseData(customerData);
            //post using rest template
             *//*   HttpEntity<Rating> request = new HttpEntity<Rating>(
                        new Rating(1, 1, 5, "Very Delicious"));
                restTemplate.postForObject("http://RATING-SERVICE/rating-service/food-delivery/addRating", request, Response.class);
*//*
            //post using feign client
            *//*    HttpEntity<Rating> request = new HttpEntity<Rating>(
                        new Rating(1, 1, 5, "Very Tasty"));
                ratingService.addRating(request.getBody());*//*

        } else {
            response.getErrMessage().add("Customer Not Found");
        }
        return response;
    }*/

