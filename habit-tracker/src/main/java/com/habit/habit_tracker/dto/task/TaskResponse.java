package com.habit.habit_tracker.dto.task;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.habit.habit_tracker.enums.TaskDifficulty;
import com.habit.habit_tracker.enums.TaskStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    private final Long id;
    private final Long userId;
    private final String name;
    private final String description;
    private final TaskStatus status;
    private final TaskDifficulty difficulty;
    private final LocalDateTime dueDate;
    private final LocalDateTime createdAt;

    public TaskResponse(
            Long id,
            Long userId,
            String name,
            String description,
            TaskStatus status,
            TaskDifficulty difficulty,
            LocalDateTime dueDate,
            LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskDifficulty getDifficulty() {
        return difficulty;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
