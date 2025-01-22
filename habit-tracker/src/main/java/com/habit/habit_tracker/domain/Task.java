package com.habit.habit_tracker.domain;

import java.time.LocalDateTime;

import com.habit.habit_tracker.enums.TaskDifficulty;
import com.habit.habit_tracker.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq")
    @SequenceGenerator(name = "tasks_seq", sequenceName = "tasks_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "description")
    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private TaskDifficulty difficulty;

    @Column(name = "date", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public Task() {

    }

    public Task(
            Long id,
            User user,
            String name,
            String description,
            TaskStatus status,
            TaskDifficulty difficulty,
            LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.status = status;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
    }

    public Task(
            User user,
            String name,
            String description,
            TaskStatus status,
            TaskDifficulty difficulty,
            LocalDateTime createdAt) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.status = status;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskDifficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(TaskDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : "null") +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", createdAt='" + createdAt + '\'';
    }
}
