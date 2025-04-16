package com.secureCrud.secureCrud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secureCrud.secureCrud.entity.User;
import com.secureCrud.secureCrud.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
     private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        System.err.println(" test-register api ");
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.err.println("-------------------------------- test-login api -----------"+user);
         String token = authService.login(user.getUsername(), user.getPassword());
          return token;
        //return authService.login(username, password);

    }
}   