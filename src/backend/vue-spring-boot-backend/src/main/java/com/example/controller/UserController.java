package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User management API")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve all users with their microposts")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
