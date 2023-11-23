package com.example.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private Integer ratingId;
    private Integer customerId;
    private Integer restaurantId;
    private int rating;
    private String feedback;
    private Restaurant restaurant;


    public Rating(Integer customerId, Integer restaurantId, int rating, String feedback) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.feedback = feedback;
    }
}
