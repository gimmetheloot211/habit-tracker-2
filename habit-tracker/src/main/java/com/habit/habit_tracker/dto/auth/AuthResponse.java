package com.habit.habit_tracker.dto.auth;

public class AuthResponse {
    private final String token;
    private final String username;
    private final String message;

    public AuthResponse(String token, String username, String message) {
        this.token = token;
        this.username = username;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return this.message;
    }
}
