package com.habit.habit_tracker.dto.logs.response.daily

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DailyHabitLogResponse(
    val habitId: Long,
    val minutesDone: Int,
    val notes: String?,
    val date: LocalDate
)