package com.expenseTracker.expenseTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test{

    @GetMapping
    public String testapi(){
        return "hi";
    }


}
