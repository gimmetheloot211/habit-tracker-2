package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.dto.auth.AuthResponse
import com.habit.habit_tracker.dto.auth.RegisterRequest
import com.habit.habit_tracker.dto.auth.LoginRequest
import com.habit.habit_tracker.dto.auth.AuthResult
import com.habit.habit_tracker.domain.User

object AuthMapper {
    fun toAuthResponse(authResult: AuthResult, message: String): AuthResponse {
        return AuthResponse(
            token = authResult.token, 
            username = authResult.username, 
            message = message
        )
    }
}