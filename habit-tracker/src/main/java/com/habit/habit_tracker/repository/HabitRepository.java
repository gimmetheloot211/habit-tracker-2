package com.habit.habit_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.habit.habit_tracker.domain.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("SELECT h FROM Habit h WHERE h.id = :habitId AND h.user.id = :userId")
    Optional<Habit> findByIdAndUserId(@Param("habitId") Long habitId, @Param("userId") Long userId);

    @Query("SELECT h FROM Habit h WHERE h.user.id = :userId")
    List<Habit> findAllByUserId(@Param("userId") Long userId);
}
