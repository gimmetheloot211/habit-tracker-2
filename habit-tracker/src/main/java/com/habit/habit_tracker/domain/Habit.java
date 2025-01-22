package com.habit.habit_tracker.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habits_seq")
    @SequenceGenerator(name = "habits_seq", sequenceName = "habits_seq", initialValue = 1, allocationSize = 1)
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

    @Column(name = "minutes_total")
    @Min(0)
    private Integer minutesTotal = 0;

    @Column(name = "date", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public Habit() {

    }

    public Habit(
            Long id,
            User user,
            String name,
            String description,
            Integer minutesTotal,
            LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.minutesTotal = minutesTotal;
        this.createdAt = createdAt;
    }

    public Habit(
            User user,
            String name,
            String description,
            Integer minutesTotal,
            LocalDateTime createdAt) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.minutesTotal = minutesTotal;
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

    public Integer getMinutesTotal() {
        return this.minutesTotal;
    }

    public void setMinutesTotal(Integer minutesTotal) {
        this.minutesTotal = minutesTotal;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : "null") +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minutesTotal='" + minutesTotal + '\'' +
                ", createdAt='" + createdAt + '\'';
    }
}
