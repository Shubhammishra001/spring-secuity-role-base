package com.secureCrud.secureCrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.secureCrud.secureCrud.entity.User;
import com.secureCrud.secureCrud.repo.UserRepository;
import com.secureCrud.secureCrud.util.JwtUtil;

@Service
public class AuthService {
@Autowired
private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String role=user.getRole().name();
        return jwtUtil.generateToken(username,role);
    }
}



