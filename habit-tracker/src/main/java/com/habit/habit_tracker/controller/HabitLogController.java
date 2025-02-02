package com.habit.habit_tracker.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.domain.DailyHabitLog;
import com.habit.habit_tracker.domain.WeeklyHabitLog;
import com.habit.habit_tracker.dto.logs.daily.DHLRequest;
import com.habit.habit_tracker.dto.logs.daily.DHLResponse;
import com.habit.habit_tracker.dto.logs.weekly.WHLRequest;
import com.habit.habit_tracker.dto.logs.weekly.WHLResponse;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.service.DailyHabitLogService;
import com.habit.habit_tracker.service.WeeklyHabitLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/logs")
public class HabitLogController {

    private final DailyHabitLogService dailyHabitLogService;
    private final WeeklyHabitLogService weeklyHabitLogService;

    public HabitLogController(
            DailyHabitLogService dailyHabitLogService,
            WeeklyHabitLogService weeklyHabitLogService) {
        this.dailyHabitLogService = dailyHabitLogService;
        this.weeklyHabitLogService = weeklyHabitLogService;
    }

    @PatchMapping("/dhl")
    public ResponseEntity<DHLResponse> createOrUpdateDailyHabitLog(@Valid @RequestBody DHLRequest request) {
        DailyHabitLog dhl = dailyHabitLogService.createOrUpdateDailyHabitLog(request);
        DHLResponse response = new DHLResponse(
                dhl.getHabit().getId(),
                dhl.getMinutesDone(),
                dhl.getNotes(),
                dhl.getDate());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dhl")
    public ResponseEntity<DHLResponse> getDailyHabitLog(
            @RequestParam("habitId") long habitId,
            @RequestParam("date") String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            DailyHabitLog dhl = dailyHabitLogService.getDailyHabitLog(habitId, date);

            DHLResponse response = new DHLResponse(
                    dhl.getHabit().getId(),
                    dhl.getMinutesDone(),
                    dhl.getNotes(),
                    dhl.getDate());

            return ResponseEntity.ok(response);
        } catch (DateTimeParseException e) {
            throw new ApiRequestException("Invalid date format. Please use YYYY-MM-DD.", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/week")
    public ResponseEntity<WHLResponse> createOrUpdateWeeklyHabitLog(@Valid @RequestBody WHLRequest request) {
        WeeklyHabitLog whl = weeklyHabitLogService.createOrUpdateWeeklyHabitLog(request);
        WHLResponse response = new WHLResponse(
                whl.getId(),
                whl.getHabit().getId(),
                whl.getWeeklyGoal(),
                whl.getDailyGoal(),
                whl.getMinutesDone(),
                whl.getWeeklyImbalance(),
                whl.getNotes(),
                whl.getStartDate(),
                whl.getEndDate());

        return ResponseEntity.ok(response);
    }
}
