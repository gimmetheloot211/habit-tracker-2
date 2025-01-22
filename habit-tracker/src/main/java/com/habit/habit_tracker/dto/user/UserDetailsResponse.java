package com.habit.habit_tracker.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsResponse {
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime lastLogin;
    private final LocalDateTime createdAt;

    public UserDetailsResponse(
            Long id,
            String username,
            String firstName,
            String lastName,
            LocalDateTime lastLogin,
            LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
