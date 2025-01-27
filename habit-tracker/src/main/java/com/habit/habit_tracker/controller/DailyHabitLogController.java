package com.habit.habit_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.domain.DailyHabitLog;
import com.habit.habit_tracker.dto.logs.daily.DHLRequest;
import com.habit.habit_tracker.dto.logs.daily.DHLResponse;
import com.habit.habit_tracker.service.DailyHabitLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dhl")
public class DailyHabitLogController {

    private final DailyHabitLogService dailyHabitLogService;

    public DailyHabitLogController(DailyHabitLogService dailyHabitLogService) {
        this.dailyHabitLogService = dailyHabitLogService;
    }

    @PatchMapping()
    public ResponseEntity<DHLResponse> createOrUpdateDailyHabitLog(@Valid @RequestBody DHLRequest request) {
        DailyHabitLog dhl = dailyHabitLogService.createOrUpdateDailyHabitLog(request);
        DHLResponse response = new DHLResponse(
                dhl.getHabit().getId(),
                dhl.getMinutesDone(),
                dhl.getNotes(),
                dhl.getDate());

        return ResponseEntity.ok(response);
    }
}
