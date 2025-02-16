package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.domain.User
import com.habit.habit_tracker.dto.user.UserDetailsResponse

object UserMapper {
    fun toUserDetailsResponse(user: User): UserDetailsResponse {
        return UserDetailsResponse(
            id = user.id!!,
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            lastLogin = user.lastLogin!!,
            createdAt = user.createdAt!!
        )
    }
}
