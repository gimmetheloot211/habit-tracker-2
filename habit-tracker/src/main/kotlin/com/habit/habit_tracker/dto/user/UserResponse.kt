package com.habit.habit_tracker.dto.user

import java.time.LocalDateTime

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDetailsResponse(
    val id: Long,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val lastLogin: LocalDateTime,
    val createdAt: LocalDateTime
)