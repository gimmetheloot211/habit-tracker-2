package com.habit.habit_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.domain.Habit;
import com.habit.habit_tracker.dto.habit.HabitCreateRequest;
import com.habit.habit_tracker.dto.habit.HabitResponse;
import com.habit.habit_tracker.dto.habit.HabitUpdateRequest;
import com.habit.habit_tracker.service.HabitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(@Valid @RequestBody HabitCreateRequest habitCreateRequest) {
        Habit habit = habitService.createHabit(habitCreateRequest);
        HabitResponse habitResponse = new HabitResponse(
                habit.getId(),
                habit.getUser().getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getMinutesTotal(),
                habit.getCreatedAt());

        return ResponseEntity.ok(habitResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HabitResponse> updateHabit(@PathVariable Long id,
            @Valid @RequestBody HabitUpdateRequest habitUpdateRequest) {
        Habit habit = habitService.updateHabit(id, habitUpdateRequest);
        HabitResponse habitResponse = new HabitResponse(
                habit.getId(),
                habit.getUser().getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getMinutesTotal(),
                habit.getCreatedAt());

        return ResponseEntity.ok(habitResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getHabit(@PathVariable Long id) {
        Habit habit = habitService.getHabit(id);
        HabitResponse habitResponse = new HabitResponse(
                habit.getId(),
                habit.getUser().getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getMinutesTotal(),
                habit.getCreatedAt());

        return ResponseEntity.ok(habitResponse);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        List<HabitResponse> habitsResponse = habits.stream().map(habit -> new HabitResponse(habit.getId(),
                habit.getUser().getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getMinutesTotal(),
                habit.getCreatedAt()))
                .toList();

        return ResponseEntity.ok(habitsResponse);
    }
}
