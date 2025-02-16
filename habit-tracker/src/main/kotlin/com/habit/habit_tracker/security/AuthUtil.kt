package com.habit.habit_tracker.security

import com.habit.habit_tracker.domain.User
import com.habit.habit_tracker.exception.ApiRequestException
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthUtil {

    fun getAuthenticatedUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw ApiRequestException("User not authenticated", HttpStatus.UNAUTHORIZED)

        val principal = authentication.principal
        if (principal !is UserPrincipal) {
            throw ApiRequestException("User not authenticated", HttpStatus.UNAUTHORIZED)
        }

        return principal.getUser()
    }
}
