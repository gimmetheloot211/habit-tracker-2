package com.habit.habit_tracker.dto.logs.weekly;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WHLRequest {

    @NotNull
    private Long habitId;

    @Min(0)
    @Max(10080)
    private Integer weeklyGoal;

    @Max(200)
    private String notes;

    @NotNull
    private LocalDate startOfWeek;

    @NotNull
    private LocalDate endOfWeek;

    public WHLRequest() {
    }

    public WHLRequest(
            Long habitId,
            Integer weeklyGoal,
            String notes,
            LocalDate startOfWeek,
            LocalDate endOfWeek) {
        this.habitId = habitId;
        this.weeklyGoal = weeklyGoal;
        this.notes = notes;
        this.startOfWeek = startOfWeek;
        this.endOfWeek = endOfWeek;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public Integer getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(Integer weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getStartOfWeek() {
        return this.startOfWeek;
    }

    public void setStartOfWeek(LocalDate startOfWeek) {
        this.startOfWeek = startOfWeek;
    }

    public LocalDate getEndOfWeek() {
        return this.endOfWeek;
    }

    public void setEndOfWeek(LocalDate endOfWeek) {
        this.endOfWeek = endOfWeek;
    }
}
