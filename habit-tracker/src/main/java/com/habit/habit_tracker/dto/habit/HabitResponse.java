package com.habit.habit_tracker.dto.habit;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HabitResponse {

    private final Long id;
    private final Long userId;
    private final String name;
    private final String description;
    private final Integer minutesTotal;
    private final LocalDateTime createdAt;

    public HabitResponse(
            Long id,
            Long userId,
            String name,
            String description,
            Integer minutesTotal,
            LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.minutesTotal = minutesTotal;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getMinutesTotal() {
        return this.minutesTotal;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
