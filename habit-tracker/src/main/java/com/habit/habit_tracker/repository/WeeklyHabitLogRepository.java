package com.habit.habit_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.habit_tracker.domain.WeeklyHabitLog;

@Repository
public interface WeeklyHabitLogRepository extends JpaRepository<WeeklyHabitLog, Long> {

}
