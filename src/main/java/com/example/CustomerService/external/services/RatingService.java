package com.example.CustomerService.external.services;


import com.example.CustomerService.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating-service/food-delivery/addRating")
    void addRating(Rating rating);


    @GetMapping("/rating-service/food-delivery/ratingsByCustomerId/{customerId}")
    List<Rating> findRatingsByCustomerId(@PathVariable int customerId);


}
