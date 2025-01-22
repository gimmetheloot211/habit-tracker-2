package com.habit.habit_tracker.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "daily_habit_logs")
public class DailyHabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dhl_seq")
    @SequenceGenerator(name = "dhl", sequenceName = "dhl_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", referencedColumnName = "id", nullable = false)
    private Habit habit;

    @Column(name = "minutes_done")
    @Min(0)
    @Max(1440)
    private Integer minutesDone = 0;

    @Column(name = "notes")
    @Size(max = 500)
    private String notes;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public DailyHabitLog() {

    }

    public DailyHabitLog(Long id, Habit habit, Integer minutesDone, String notes, LocalDate date) {
        this.id = id;
        this.habit = habit;
        this.minutesDone = minutesDone;
        this.notes = notes;
        this.date = date;
    }

    public DailyHabitLog(Habit habit, Integer minutesDone, String notes, LocalDate date) {
        this.habit = habit;
        this.minutesDone = minutesDone;
        this.notes = notes;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habit getHabit() {
        return this.habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public Integer getMinutesDone() {
        return this.minutesDone;
    }

    public void setMinutesDone(Integer minutesDone) {
        this.minutesDone = minutesDone;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailyHabitLog{" +
                "id=" + id +
                ", habitId=" + (habit != null ? habit.getId() : "null") +
                ", minutesDone='" + minutesDone + '\'' +
                ", notes='" + notes + '\'' +
                ", date='" + date + '\'';
    }
}
