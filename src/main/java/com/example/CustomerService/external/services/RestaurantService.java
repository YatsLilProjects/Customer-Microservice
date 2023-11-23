package com.example.CustomerService.external.services;


import com.example.CustomerService.dto.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantService {
    @GetMapping("/restaurant-service/food-delivery/viewRestaurantById/{restaurantId}")
    Restaurant getRestaurant(@PathVariable int restaurantId);
}
