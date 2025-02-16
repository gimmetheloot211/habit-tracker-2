package com.habit.habit_tracker.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import com.habit.habit_tracker.exception.ApiRequestException

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException::class)
    fun handleApiRequestException(exception: ApiRequestException): ResponseEntity<String> {
        return ResponseEntity.status(exception.status).body(exception.message)
    }
}