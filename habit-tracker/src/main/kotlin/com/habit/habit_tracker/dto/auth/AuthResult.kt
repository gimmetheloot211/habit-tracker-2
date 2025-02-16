package com.habit.habit_tracker.dto.auth

data class AuthResult(
    val token: String,
    val username: String
)