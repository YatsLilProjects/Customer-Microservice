//package com.example.CustomerService.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Positive;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "food_cart")
//public class FoodCart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "food_cart_generator")
//    @SequenceGenerator(
//            name = "food_cart_generator",
//            sequenceName = "food_cart_sequence_name",
//            allocationSize = 1
//    )
//    @Column(name = "cart_id")
//    private Integer cartId;
//
//    private List<String> itemList;
//
//    @Column(name = "total_cost")
//    @Positive
//    private double totalCost;
//
//    @OneToOne
//    private Customer customer;
//
//    @ManyToOne
//    private Restaurant restaurant;
//
//}
