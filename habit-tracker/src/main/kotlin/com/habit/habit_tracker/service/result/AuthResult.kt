package com.habit.habit_tracker.service.result

import com.habit.habit_tracker.domain.User

data class AuthResult(
    val user : User,
    val token : String
)