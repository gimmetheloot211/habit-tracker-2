package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.dto.auth.response.AuthResponse

object AuthMapper {
    fun toAuthResponse(authResponse: AuthResponse): AuthResponse {
        return AuthResponse(
            token = authResponse.token,
            username = authResponse.username,
            message = authResponse.message
        )
    }
}