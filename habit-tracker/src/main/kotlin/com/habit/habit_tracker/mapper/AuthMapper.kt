package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.dto.auth.response.AuthResponse
import com.habit.habit_tracker.service.result.AuthResult

object AuthMapper {
    fun toAuthResponse(authResult: AuthResult): AuthResponse {
        return AuthResponse(
            authResult.token,
            authResult.user.username
        )
    }
}