package com.example.auction.controllers;

import com.example.auction.model.dto.GetUserResponse;
import com.example.auction.model.dto.RegisterUserRequest;
import com.example.auction.model.dto.RegisterUserResponse;
import com.example.auction.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return userService.registerUser(registerUserRequest);
    }

    @GetMapping("/user/{userEmail}")
    public GetUserResponse getUser(@PathVariable(name = "userEmail") String userEmail) {
        return userService.getUser(userEmail);
    }
}
