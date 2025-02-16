package com.habit.habit_tracker.dto.auth

data class AuthResponse(
    val token: String,
    val username: String,
    val message: String
)