package com.habit.habit_tracker.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.Habit;
import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.domain.WeeklyHabitLog;
import com.habit.habit_tracker.dto.logs.weekly.WHLRequest;
import com.habit.habit_tracker.events.DHLUpdatedEvent;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.HabitRepository;
import com.habit.habit_tracker.repository.WeeklyHabitLogRepository;
import com.habit.habit_tracker.security.AuthUtil;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

@Service
public class WeeklyHabitLogService {
    private final WeeklyHabitLogRepository weeklyHabitLogRepository;
    private final HabitRepository habitRepository;
    private final AuthUtil authUtil;
    private static final Logger log = LoggerFactory.getLogger(WeeklyHabitLogService.class);

    public WeeklyHabitLogService(
            WeeklyHabitLogRepository weeklyHabitLogRepository,
            HabitRepository habitRepository,
            AuthUtil authUtil) {
        this.weeklyHabitLogRepository = weeklyHabitLogRepository;
        this.habitRepository = habitRepository;
        this.authUtil = authUtil;
    }

    @EventListener
    public void handleDHLUpdated(DHLUpdatedEvent event) {
        try {
            processHabitLog(event.getHabitId(), event.getMinutesDoneChange(), event.getDate());
        } catch (Exception e) {
            log.error("Failed to process weekly habit log update for habit ID: " + event.getHabitId(), e);
        }

    }

    private void processHabitLog(Long habitId, Integer minutesDoneChange, LocalDate date) {
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        WeeklyHabitLog weeklyLog = weeklyHabitLogRepository.findByHabitAndDate(habitId, startOfWeek, endOfWeek)
                .orElse(new WeeklyHabitLog());

        weeklyLog.setHabit(habitRepository.findById(habitId)
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND)));
        weeklyLog.setStartDate(startOfWeek);
        weeklyLog.setEndDate(endOfWeek);
        weeklyLog.setMinutesDone(weeklyLog.getMinutesDone() + minutesDoneChange);

        weeklyHabitLogRepository.save(weeklyLog);
    }

    public WeeklyHabitLog createOrUpdateWeeklyHabitLog(WHLRequest request) {
        User user = authUtil.getAuthenticatedUser();
        Habit habit = habitRepository.findById(request.getHabitId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new ApiRequestException(UNAUTHORIZED, HttpStatus.FORBIDDEN);
        }

        WeeklyHabitLog weeklyLog = weeklyHabitLogRepository.findByHabitAndDate(
                request.getHabitId(),
                request.getStartOfWeek(),
                request.getEndOfWeek())
                .orElse(new WeeklyHabitLog());

        weeklyLog.setHabit(habit);
        weeklyLog.setStartDate(request.getStartOfWeek());
        weeklyLog.setEndDate(request.getEndOfWeek());

        if (request.getWeeklyGoal() != null) {
            weeklyLog.setWeeklyGoal(request.getWeeklyGoal());
            weeklyLog.setDailyGoal(request.getWeeklyGoal() / 7);
            weeklyLog.setWeeklyImbalance(weeklyLog.getMinutesDone() - request.getWeeklyGoal());
        }

        return weeklyHabitLogRepository.save(weeklyLog);
    }
}
