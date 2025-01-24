package com.habit.habit_tracker.dto.task;

import com.habit.habit_tracker.enums.TaskDifficulty;
import com.habit.habit_tracker.enums.TaskStatus;

import jakarta.validation.constraints.Size;

public class TaskUpdateRequest {

    @Size(max = 50, message = "Task name cannot exceed 50 characters")
    private String name;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    private TaskStatus status;

    private TaskDifficulty difficulty;

    public TaskUpdateRequest() {

    }

    public TaskUpdateRequest(
            String name,
            String description,
            TaskStatus status,
            TaskDifficulty difficulty) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.difficulty = difficulty;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(TaskDifficulty difficulty) {
        this.difficulty = difficulty;
    }
}
