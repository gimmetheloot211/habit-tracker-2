package com.habit.habit_tracker.repository

import com.habit.habit_tracker.domain.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TaskRepository : JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.id = :taskId AND t.user.id = :userId")
    fun findByIdAndUserId(@Param("taskId") taskId: Long, @Param("userId") userId: Long): Optional<Task>

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    fun findAllByUserId(@Param("userId") userId: Long): List<Task>
}
