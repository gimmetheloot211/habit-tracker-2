package com.habit.habit_tracker.dto.user.request

data class UserUpdateRequest(
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null
)