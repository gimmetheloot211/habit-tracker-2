package com.habit.habit_tracker.security

import org.springframework.http.HttpStatus
import javax.naming.AuthenticationException

class JwtAuthenticationException(
    message: String,
    val httpStatus: HttpStatus? = null
) : AuthenticationException(message)
