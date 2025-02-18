package com.habit.habit_tracker.mapper

import com.habit.habit_tracker.domain.DailyHabitLog
import com.habit.habit_tracker.domain.WeeklyHabitLog
import com.habit.habit_tracker.domain.Habit

import com.habit.habit_tracker.dto.logs.daily.DailyHabitLogResponse
import com.habit.habit_tracker.dto.logs.weekly.WeeklyHabitLogResponse
import com.habit.habit_tracker.dto.habit.HabitResponse
import com.habit.habit_tracker.dto.logs.FullHabitLogsForWeek


object FullHabitLogsForWeekMapper {
    fun toFullHabitLogsForWeek(d: List<DailyHabitLog>, w: WeeklyHabitLog?, h: Habit): FullHabitLogsForWeek {
        val dailyHabitLogs = d.map { DailyHabitLogMapper.toDailyHabitLogResponse(it)}
        val weeklyHabitLogResponse = WeeklyHabitLogMapper.toWeeklyHabitLogResponse(w)
        val habitResponse = HabitMapper.toHabitResponse(h)

        return FullHabitLogsForWeek(dailyHabitLogs, weeklyHabitLogResponse, habitResponse)
    }
}