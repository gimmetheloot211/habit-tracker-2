package com.habit.habit_tracker.dto.habit;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HabitUpdateRequest {

    @Size(max = 50, message = "Habit name should not exceed 50 characters")
    private String name;

    @Size(max = 200, message = "Habit description should not exceed 200 characters")
    private String description;

    public HabitUpdateRequest() {

    }

    public HabitUpdateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
