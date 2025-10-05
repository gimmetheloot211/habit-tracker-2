package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.domain.WeeklyHabitLog
import com.habit.habit_tracker.dto.logs.response.weekly.WeeklyHabitLogResponse

object WeeklyHabitLogMapper {
    fun toWeeklyHabitLogResponse(log: WeeklyHabitLog?): WeeklyHabitLogResponse? {
        return log?.let {
            WeeklyHabitLogResponse(
                id = it.id!!,
                habitId = it.habit.id!!,
                weeklyGoal = it.weeklyGoal,
                dailyGoal = it.dailyGoal,
                minutesDone = it.minutesDone,
                weeklyImbalance = it.weeklyImbalance,
                notes = it.notes,
                weekStart = it.weekStart,
                weekEnd = it.weekEnd
            )
        }
    }
}