package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.domain.WeeklyHabitLog
import com.habit.habit_tracker.dto.logs.weekly.WeeklyHabitLogResponse

object WeeklyHabitLogMapper {
    fun toWeeklyHabitLogResponse(log: WeeklyHabitLog): WeeklyHabitLogResponse {
        return WeeklyHabitLogResponse(
            id = log.id!!,
            habitId = log.habit.id!!,
            weeklyGoal = log.weeklyGoal,
            dailyGoal = log.dailyGoal,
            minutesDone = log.minutesDone,
            weeklyImbalance = log.weeklyImbalance,
            notes = log.notes,
            weekStart = log.weekStart,
            weekEnd = log.weekEnd
        )
    }
}