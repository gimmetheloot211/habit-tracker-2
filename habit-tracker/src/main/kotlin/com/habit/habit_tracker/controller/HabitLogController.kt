package com.habit.habit_tracker.controller

import com.habit.habit_tracker.dto.logs.daily.DailyHabitLogRequest
import com.habit.habit_tracker.dto.logs.daily.DailyHabitLogResponse
import com.habit.habit_tracker.dto.logs.weekly.WeeklyHabitLogRequest
import com.habit.habit_tracker.dto.logs.weekly.WeeklyHabitLogResponse
import com.habit.habit_tracker.mapper.DailyHabitLogMapper
import com.habit.habit_tracker.mapper.WeeklyHabitLogMapper
import com.habit.habit_tracker.exception.ApiRequestException
import com.habit.habit_tracker.service.DailyHabitLogService
import com.habit.habit_tracker.service.WeeklyHabitLogService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeParseException

@RestController
@RequestMapping("api/v1/logs")
class HabitLogController(
    private val dailyHabitLogService: DailyHabitLogService,
    private val weeklyHabitLogService: WeeklyHabitLogService
) {

    @PatchMapping("/daily/{habitId}")
    fun createOrUpdateDailyHabitLog(
        @PathVariable habitId: Long, 
        @Valid @RequestBody request: DailyHabitLogRequest
        ): ResponseEntity<DailyHabitLogResponse> {
        val dailyHabitLog = dailyHabitLogService.createOrUpdateDailyHabitLog(habitId, request)
        val response = DailyHabitLogMapper.toDailyHabitLogResponse(dailyHabitLog)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/daily")
    fun getDailyHabitLog(
        @RequestParam("habitId") habitId: Long,
        @RequestParam("date") dateString: String
    ): ResponseEntity<DailyHabitLogResponse> {
        return try {
            val date = LocalDate.parse(dateString)
            val dailyHabitLog = dailyHabitLogService.getDailyHabitLog(habitId, date)
            val response = DailyHabitLogMapper.toDailyHabitLogResponse(dailyHabitLog)
            ResponseEntity.ok(response)
        } catch (e: DateTimeParseException) {
            throw ApiRequestException("Invalid date format. Please use YYYY-MM-DD.", HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/weekly/{habitId}")
    fun createOrUpdateWeeklyHabitLog(
        @PathVariable habitId: Long, 
        @Valid @RequestBody request: WeeklyHabitLogRequest
        ): ResponseEntity<WeeklyHabitLogResponse> {
        val weeklyHabitLog = weeklyHabitLogService.createOrUpdateWeeklyHabitLog(habitId, request)
        val response = WeeklyHabitLogMapper.toWeeklyHabitLogResponse(weeklyHabitLog)
        return ResponseEntity.ok(response)
    }
}
