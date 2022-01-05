package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.requests.LoginRequest;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class LoginController {
    private UserService userService;

    @PostMapping
    public User login(@RequestBody LoginRequest request) {
        System.out.println(request);
        return userService.login(request);
    }
}
