package com.habit.habit_tracker.repository;

import java.time.LocalDate
import java.util.Optional

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import com.habit.habit_tracker.domain.WeeklyHabitLog

@Repository
interface WeeklyHabitLogRepository : JpaRepository<WeeklyHabitLog, Long> {
    @Query("SELECT w FROM WeeklyHabitLog w WHERE w.habit.id = :habitId AND w.weekStart = :weekStart AND w.weekEnd = :weekEnd")
    fun findByHabitAndDate(
        @Param("habitId") habitId: Long,
        @Param("weekStart") weekStart: LocalDate,
        @Param("weekEnd") weekEnd: LocalDate,
    ): Optional<WeeklyHabitLog>
}