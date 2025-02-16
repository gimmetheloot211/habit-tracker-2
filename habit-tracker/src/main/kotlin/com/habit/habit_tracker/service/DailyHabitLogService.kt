package com.habit.habit_tracker.service

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale
import java.util.Optional

import com.habit.habit_tracker.constants.ErrorMessage.USER_NOT_FOUND
import com.habit.habit_tracker.constants.ErrorMessage.HABIT_NOT_FOUND
import com.habit.habit_tracker.constants.ErrorMessage.DHL_NOT_FOUND

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

import com.habit.habit_tracker.domain.DailyHabitLog
import com.habit.habit_tracker.domain.Habit
import com.habit.habit_tracker.domain.User
import com.habit.habit_tracker.dto.logs.daily.DailyHabitLogRequest
import com.habit.habit_tracker.events.EventPublisher
import com.habit.habit_tracker.exception.ApiRequestException
import com.habit.habit_tracker.repository.DailyHabitLogRepository
import com.habit.habit_tracker.repository.HabitRepository
import com.habit.habit_tracker.security.AuthUtil

import jakarta.transaction.Transactional

@Service
class DailyHabitLogService(
    private val dailyHabitLogRepository: DailyHabitLogRepository,
    private val habitRepository: HabitRepository,
    private val authUtil: AuthUtil,
    private val eventPublisher: EventPublisher
) {
    @Transactional
    fun createOrUpdateDailyHabitLog(habitId: Long, request: DailyHabitLogRequest): DailyHabitLog {
        val user = authUtil.getAuthenticatedUser()
        val habit = habitRepository.findByIdAndUserId(habitId, user.id!!)
            .orElseThrow { ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND)}

        val existingLog = dailyHabitLogRepository.findByHabitAndDate(habitId, request.date).orElse(null)

        val dhl = existingLog?.apply {
            request.notes?.let { notes = it }
            request.minutesDone?.let { newMinutes -> // not using "it" to avoid confusion 
                val minutesDoneChange = newMinutes - minutesDone // calculating the change for the event
                minutesDone = newMinutes // updating the dhl minutesDone
                eventPublisher.publishDailyHabitLogUpdated(habit.user.id!!, minutesDoneChange, date)
            }
        } ?: DailyHabitLog(
            habit = habit,
            date = request.date,
            minutesDone = request.minutesDone ?: 0,
            notes = request.notes
        ).also {
            if (request.minutesDone != null) {
                eventPublisher.publishDailyHabitLogUpdated(it.habit.user.id!!, it.minutesDone, it.date)
            }
        }

        return dailyHabitLogRepository.save(dhl)

        // val dhlOptional = dailyHabitLogRepository.findByHabitAndDate(habitId, request.date)
        // var dhl: DailyHabitLog

        // if (dhlOptional.isPresent()) {
        //     dhl = dhlOptional.get()
        //     dhl.apply {
        //         request.notes?.let { notes = it }
        //         request.minutesDone?.let { 
        //             val minutesDoneChange: Int = it - dhl.minutesDone // Calculating the change for the event
        //             dhl.minutesDone = it                              // before assigning it to the log 
        //             eventPublisher.publishDailyHabitLogUpdated(dhl.habit.user.id!!, minutesDoneChange, dhl.date)
        //         }
        //     }
        // } else {
        //     dhl = DailyHabitLog(
        //         habit = habit,
        //         date = request.date,
        //         minutesDone = request.minutesDone ?: 0,
        //         notes = request.notes
        //     )
        //     if (request.minutesDone != null) {
        //         eventPublisher.publishDailyHabitLogUpdated(dhl.habit.user.id!!, dhl.minutesDone, dhl.date)
        //     }
        // }

        // return dailyHabitLogRepository.save(dhl)
    }

    fun getDailyHabitLog(habitId: Long, date: LocalDate): DailyHabitLog {
        val user = authUtil.getAuthenticatedUser()
        habitRepository.findByIdAndUserId(habitId, user.id!!)
            .orElseThrow { ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND)}

        return dailyHabitLogRepository.findByHabitAndDate(habitId, date)
            .orElseThrow { ApiRequestException(DHL_NOT_FOUND, HttpStatus.NOT_FOUND)}
    }

    fun getDailyHabitLogsForWeek(habitId: Long, year: Int, weekNumber: Int): List<DailyHabitLog> {
        val user = authUtil.getAuthenticatedUser()
        habitRepository.findByIdAndUserId(habitId, user.id!!)
            .orElseThrow { ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND)}

        val weekFields: WeekFields = WeekFields.of(Locale.getDefault())
        val weekStart: LocalDate = LocalDate.of(year, 1, 1)
            .with(weekFields.weekOfYear(), weekNumber.toLong())
            .with(DayOfWeek.MONDAY)

        val weekEnd: LocalDate = weekStart.plusDays(6)

        return dailyHabitLogRepository.findDailyHabitLogsForWeek(habitId, weekStart, weekEnd)
    }
}