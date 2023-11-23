package com.example.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantContactNo;
    private String restaurantLocation;
    private String restaurantImage;
    private List<CuisineType> restaurantCuisineType;
    private List<MenuItem> menuItems;

}


