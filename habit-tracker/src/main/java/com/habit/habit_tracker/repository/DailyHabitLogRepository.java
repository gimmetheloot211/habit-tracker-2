package com.habit.habit_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.habit.habit_tracker.domain.DailyHabitLog;

@Repository
public interface DailyHabitLogRepository extends JpaRepository<DailyHabitLog, Long> {
    @Query("SELECT d FROM DailyHabitLog d WHERE d.habit.id = :habitId AND d.date = :date")
    Optional<DailyHabitLog> findByHabitAndDate(@Param("habitId") Long habitId, @Param("date") LocalDate date);

    @Query("SELECT d FROM DailyHabitLog d WHERE d.habit.id = :habitId AND d.date BETWEEN :startOfWeek AND :endOfWeek")
    List<DailyHabitLog> findDailyHabitLogsForWeek(
            @Param("habitId") Long habitId,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek);
}
