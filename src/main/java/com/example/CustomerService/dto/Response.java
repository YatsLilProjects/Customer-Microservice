package com.example.CustomerService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success;
    private List<String> errMessage=new ArrayList<>();
    private Object responseData;
}
