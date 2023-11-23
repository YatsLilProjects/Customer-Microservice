package com.example.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    private Integer menuItemId;
    private String menuItemName;
    private double menuItemPrice;
    private String menuItemDesc;
    private String menuItemImage;
    private boolean vegetarian;

}
