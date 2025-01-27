package com.habit.habit_tracker.dto.logs.weekly;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WHLResponse {

    private final Long id;
    private final Long habitId;
    private final Integer weeklyGoal;
    private final Integer dailyGoal;
    private final Integer minutesDone;
    private final Integer weeklyImbalance;
    private final String notes;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public WHLResponse(
            Long id,
            Long habitId,
            Integer weeklyGoal,
            Integer dailyGoal,
            Integer minutesDone,
            Integer weeklyImbalance,
            String notes,
            LocalDate startDate,
            LocalDate endDate) {
        this.id = id;
        this.habitId = habitId;
        this.weeklyGoal = weeklyGoal;
        this.dailyGoal = dailyGoal;
        this.minutesDone = minutesDone;
        this.weeklyImbalance = weeklyImbalance;
        this.notes = notes;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public Long getHabitId() {
        return habitId;
    }

    public Integer getWeeklyGoal() {
        return weeklyGoal;
    }

    public Integer getDailyGoal() {
        return dailyGoal;
    }

    public Integer getMinutesDone() {
        return minutesDone;
    }

    public Integer getWeeklyImbalance() {
        return weeklyImbalance;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
