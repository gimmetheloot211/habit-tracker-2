package com.habit.habit_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.user.UserDetailsResponse;
import com.habit.habit_tracker.dto.user.UserUpdateRequest;
import com.habit.habit_tracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailsResponse> getUserDetails() {
        User user = userService.getUserDetails();
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getLastLogin(),
                user.getCreatedAt());

        return ResponseEntity.ok(userDetailsResponse);
    }

    @PatchMapping
    public ResponseEntity<UserDetailsResponse> updateUserDetails(
            @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User updatedUser = userService.updateUserDetails(userUpdateRequest);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getLastLogin(),
                updatedUser.getCreatedAt());

        return ResponseEntity.ok(userDetailsResponse);
    }
}
