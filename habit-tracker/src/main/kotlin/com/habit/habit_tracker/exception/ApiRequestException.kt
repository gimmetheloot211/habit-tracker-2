package com.habit.habit_tracker.exception

import org.springframework.http.HttpStatus

class ApiRequestException(
    message: String, 
    val status: HttpStatus
) : RuntimeException(message)
