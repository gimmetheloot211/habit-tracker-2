package com.habit.habit_tracker.events

import java.time.LocalDate

data class DailyHabitLogUpdatedEvent(
    val habitId: Long,
    val minutesDoneChange: Int,
    val date: LocalDate
)
