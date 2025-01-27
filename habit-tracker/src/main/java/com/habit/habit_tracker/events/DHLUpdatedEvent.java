package com.habit.habit_tracker.events;

import java.time.LocalDate;

public class DHLUpdatedEvent {
    private final Long habitId;
    private final Integer minutesDoneChange;
    private final LocalDate date;

    public DHLUpdatedEvent(Long habitId, Integer minutesDoneChange, LocalDate date) {
        this.habitId = habitId;
        this.minutesDoneChange = minutesDoneChange;
        this.date = date;
    }

    public Long getHabitId() {
        return habitId;
    }

    public Integer getMinutesDoneChange() {
        return minutesDoneChange;
    }

    public LocalDate getDate() {
        return date;
    }
}
