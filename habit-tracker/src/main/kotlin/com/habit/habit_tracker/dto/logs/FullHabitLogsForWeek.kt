package com.habit.habit_tracker.dto.logs

import com.fasterxml.jackson.annotation.JsonInclude
import com.habit.habit_tracker.dto.habit.response.HabitResponse
import com.habit.habit_tracker.dto.logs.daily.DailyHabitLogResponse
import com.habit.habit_tracker.dto.logs.weekly.WeeklyHabitLogResponse

@JsonInclude(JsonInclude.Include.NON_NULL)
data class FullHabitLogsForWeek(
    val dailyHabitLogs: List<DailyHabitLogResponse>,
    val weeklyHabitLog: WeeklyHabitLogResponse?,
    val habit: HabitResponse,
)