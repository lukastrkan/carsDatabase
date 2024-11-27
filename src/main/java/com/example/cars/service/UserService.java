package com.example.cars.service;

import com.example.cars.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User save(User user);
}
