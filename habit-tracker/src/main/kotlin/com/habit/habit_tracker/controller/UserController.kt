package com.habit.habit_tracker.controller

import com.habit.habit_tracker.dto.user.UserDetailsResponse
import com.habit.habit_tracker.dto.user.UserUpdateRequest
import com.habit.habit_tracker.mapper.UserMapper
import com.habit.habit_tracker.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUserDetails(): ResponseEntity<UserDetailsResponse> {
        val user = userService.getUserDetails()
        val response = UserMapper.toUserDetailsResponse(user)
        return ResponseEntity.ok(response)
    }

    @PatchMapping
    fun updateUserDetails(@Valid @RequestBody request: UserUpdateRequest): ResponseEntity<UserDetailsResponse> {
        val updatedUser = userService.updateUserDetails(request)
        val response = UserMapper.toUserDetailsResponse(updatedUser)
        return ResponseEntity.ok(response)
    }
}
