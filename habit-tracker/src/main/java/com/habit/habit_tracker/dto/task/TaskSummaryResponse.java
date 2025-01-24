package com.habit.habit_tracker.dto.task;

import java.time.LocalDateTime;
import com.habit.habit_tracker.enums.TaskDifficulty;
import com.habit.habit_tracker.enums.TaskStatus;

public class TaskSummaryResponse {
    private final Long id;
    private final String name;
    private final TaskStatus status;
    private final TaskDifficulty difficulty;
    private final LocalDateTime dueDate;

    public TaskSummaryResponse(Long id, String name, TaskStatus status, TaskDifficulty difficulty,
            LocalDateTime dueDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public TaskDifficulty getDifficulty() {
        return this.difficulty;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
}
