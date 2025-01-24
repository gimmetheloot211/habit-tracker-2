package com.habit.habit_tracker.dto.task;

import java.time.LocalDateTime;
import com.habit.habit_tracker.enums.TaskDifficulty;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskCreateRequest {
    @NotBlank(message = "Task name cannot be empty")
    @Size(max = 50, message = "Task name cannot exceed 50 characters")
    private String name;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    @NotNull(message = "Task difficulty is required")
    private TaskDifficulty difficulty;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    public TaskCreateRequest() {
    }

    public TaskCreateRequest(
            String name,
            String description,
            TaskDifficulty difficulty,
            LocalDateTime dueDate) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(TaskDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
