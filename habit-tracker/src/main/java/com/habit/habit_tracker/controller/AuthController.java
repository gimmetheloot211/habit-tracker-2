package com.habit.habit_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.dto.auth.AuthResponse;
import com.habit.habit_tracker.dto.auth.LoginRequest;
import com.habit.habit_tracker.dto.auth.RegisterRequest;
import com.habit.habit_tracker.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        String token = authService.registerUser(registerRequest);
        String username = registerRequest.getUsername();
        String message = "User created successfully";

        AuthResponse authResponse = new AuthResponse(token, username, message);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.loginUser(loginRequest);
        String username = loginRequest.getUsername();
        String message = "Logged in successfully";

        AuthResponse authResponse = new AuthResponse(token, username, message);
        return ResponseEntity.ok(authResponse);
    }

}
