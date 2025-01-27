package com.habit.habit_tracker.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.habit.habit_tracker.domain.WeeklyHabitLog;

@Repository
public interface WeeklyHabitLogRepository extends JpaRepository<WeeklyHabitLog, Long> {

    @Query("SELECT w FROM WeeklyHabitLog w WHERE w.habit.id = :habitId AND w.startDate = :startDate AND w.endDate = :endDate")
    Optional<WeeklyHabitLog> findByHabitAndDate(
            @Param("habitId") Long habitId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
