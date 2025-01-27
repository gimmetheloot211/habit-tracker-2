package com.habit.habit_tracker.dto.logs.daily;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DHLRequest {

    @NotNull
    private Long habitId;

    @Min(0)
    @Max(1440)
    private Integer minutesDone;

    @Size(max = 200)
    private String notes;

    @NotNull
    private LocalDate date;

    public DHLRequest() {
    }

    public DHLRequest(Long habitId, Integer minutesDone, String notes, LocalDate date) {
        this.habitId = habitId;
        this.minutesDone = minutesDone;
        this.notes = notes;
        this.date = date;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public Integer getMinutesDone() {
        return minutesDone;
    }

    public void setMinutesDone(Integer minutesDone) {
        this.minutesDone = minutesDone;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
