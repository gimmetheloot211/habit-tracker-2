package com.habit.habit_tracker.dto.logs.daily;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DHLResponse {

    private final Long habitId;
    private final Integer minutesDone;
    private final String notes;
    private final LocalDate date;

    public DHLResponse(Long habitId, Integer minutesDone, String notes, LocalDate date) {
        this.habitId = habitId;
        this.minutesDone = minutesDone;
        this.notes = notes;
        this.date = date;
    }

    public Long getHabitId() {
        return habitId;
    }

    public Integer getMinutesDone() {
        return minutesDone;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getDate() {
        return date;
    }
}
