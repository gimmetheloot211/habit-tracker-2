package com.habit.habit_tracker.repository;

import java.time.LocalDate
import java.util.Optional

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import com.habit.habit_tracker.domain.DailyHabitLog

interface DailyHabitLogRepository : JpaRepository<DailyHabitLog, Long> {

    @Query("SELECT d FROM DailyHabitLog d WHERE d.habit.id = :habitId AND d.date = :date")
    fun findByHabitAndDate(@Param("habitId") habitId: Long, @Param("date") date: LocalDate): Optional<DailyHabitLog>

    @Query("SELECT d FROM DailyHabitLog d WHERE d.habit.id = :habitId AND d.date BETWEEN :weekStart AND :weekEnd")
    fun findDailyHabitLogsForWeek(
        @Param("habitId") habitId: Long,
        @Param("weekStart") weekStart: LocalDate,
        @Param("weekEnd") weekEnd: LocalDate
    ): List<DailyHabitLog>

}