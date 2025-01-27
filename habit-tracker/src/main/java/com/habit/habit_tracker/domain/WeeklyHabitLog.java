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
@Table(name = "weekly_habit_logs")
public class WeeklyHabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "whl_seq")
    @SequenceGenerator(name = "whl_seq", sequenceName = "whl_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", referencedColumnName = "id", nullable = false)
    private Habit habit;

    @Column(name = "weekly_goal")
    @Min(0)
    @Max(10080)
    private Integer weeklyGoal;

    @Column(name = "daily_goal")
    @Min(0)
    @Max(1440)
    private Integer dailyGoal;

    @Column(name = "minutes_done")
    @Min(0)
    @Max(10080)
    private Integer minutesDone = 0;

    @Column(name = "weekly_imbalance")
    private Integer weeklyImbalance;

    @Column(name = "notes")
    @Size(max = 200)
    private String notes;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    public WeeklyHabitLog() {

    }

    public WeeklyHabitLog(
            Long id,
            Habit habit,
            Integer weeklyGoal,
            Integer dailyGoal,
            Integer minutesDone,
            Integer weeklyImbalance,
            String notes,
            LocalDate startDate,
            LocalDate endDate) {
        this.id = id;
        this.habit = habit;
        this.weeklyGoal = weeklyGoal;
        this.dailyGoal = dailyGoal;
        this.minutesDone = minutesDone;
        this.weeklyImbalance = weeklyImbalance;
        this.notes = notes;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WeeklyHabitLog(
            Habit habit,
            Integer weeklyGoal,
            Integer dailyGoal,
            Integer minutesDone,
            Integer weeklyImbalance,
            String notes,
            LocalDate startDate,
            LocalDate endDate) {
        this.habit = habit;
        this.weeklyGoal = weeklyGoal;
        this.dailyGoal = dailyGoal;
        this.minutesDone = minutesDone;
        this.weeklyImbalance = weeklyImbalance;
        this.notes = notes;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public Integer getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(Integer weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public Integer getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(Integer dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public Integer getMinutesDone() {
        return minutesDone;
    }

    public void setMinutesDone(Integer minutesDone) {
        this.minutesDone = minutesDone;
    }

    public Integer getWeeklyImbalance() {
        return weeklyImbalance;
    }

    public void setWeeklyImbalance(Integer weeklyImbalance) {
        this.weeklyImbalance = weeklyImbalance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "WeeklyHabitLog{" +
                "id=" + id +
                ", habitId=" + (habit != null ? habit.getId() : "null") +
                ", weeklyGoal=" + weeklyGoal +
                ", dailyGoal=" + dailyGoal +
                ", minutesDone=" + minutesDone +
                ", weeklyImbalance=" + weeklyImbalance +
                ", notes='" + notes + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
