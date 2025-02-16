package com.habit.habit_tracker.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "weekly_habit_logs")
data class WeeklyHabitLog(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "whl_seq")
    @SequenceGenerator(name = "whl_seq", sequenceName = "whl_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", referencedColumnName = "id", nullable = false)
    val habit: Habit,

    @Column(name = "weekly_goal")
    @field:Min(0)
    @field:Max(10080)
    var weeklyGoal: Int? = null,

    @Column(name = "daily_goal")
    @field:Min(0)
    @field:Max(1440)
    var dailyGoal: Int? = null,

    @Column(name = "minutes_done")
    @field:Min(0)
    @field:Max(10080)
    var minutesDone: Int = 0,

    @Column(name = "weekly_imbalance")
    var weeklyImbalance: Int? = null,

    @Column(name = "notes")
    @field:Size(max = 200)
    var notes: String? = null,

    @Column(name = "start_date", nullable = false)
    val weekStart: LocalDate,

    @Column(name = "end_date", nullable = false)
    val weekEnd: LocalDate,

    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now()
        }
    }
}