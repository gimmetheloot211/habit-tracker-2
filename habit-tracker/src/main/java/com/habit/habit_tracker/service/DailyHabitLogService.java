package com.habit.habit_tracker.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.DailyHabitLog;
import com.habit.habit_tracker.domain.Habit;
import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.logs.daily.DHLRequest;
import com.habit.habit_tracker.events.EventPublisher;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.DailyHabitLogRepository;
import com.habit.habit_tracker.repository.HabitRepository;
import com.habit.habit_tracker.security.AuthUtil;

import jakarta.transaction.Transactional;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

@Service
public class DailyHabitLogService {

    private final DailyHabitLogRepository dailyHabitLogRepository;
    private final HabitRepository habitRepository;
    private final AuthUtil authUtil;
    private final EventPublisher eventPublisher;

    public DailyHabitLogService(
            DailyHabitLogRepository dailyHabitLogRepository,
            AuthUtil authUtil,
            HabitRepository habitRepository,
            EventPublisher eventPublisher) {
        this.dailyHabitLogRepository = dailyHabitLogRepository;
        this.habitRepository = habitRepository;
        this.authUtil = authUtil;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public DailyHabitLog createOrUpdateDailyHabitLog(DHLRequest request) {
        User user = authUtil.getAuthenticatedUser();
        Habit habit = habitRepository.findByIdAndUserId(request.getHabitId(), user.getId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));

        Optional<DailyHabitLog> dhlOptional = dailyHabitLogRepository.findByHabitAndDate(request.getHabitId(),
                request.getDate());

        DailyHabitLog dhl;

        if (dhlOptional.isPresent()) {
            dhl = dhlOptional.get();
            if (request.getMinutesDone() != null) {
                Integer minutesDoneChange = request.getMinutesDone() - dhl.getMinutesDone();
                dhl.setMinutesDone(request.getMinutesDone());
                eventPublisher.publishDHLUpdated(dhl.getHabit().getId(), minutesDoneChange, dhl.getDate());
            }
            if (request.getNotes() != null) {
                dhl.setNotes(request.getNotes());
            }
        } else {
            dhl = new DailyHabitLog();
            dhl.setHabit(habit);
            if (request.getMinutesDone() != null) {
                dhl.setMinutesDone(request.getMinutesDone());
                eventPublisher.publishDHLUpdated(dhl.getHabit().getId(), dhl.getMinutesDone(), dhl.getDate());
            }
            if (request.getNotes() != null) {
                dhl.setNotes(request.getNotes());
            }
            dhl.setDate(request.getDate());

        }

        return dailyHabitLogRepository.save(dhl);
    }

    public DailyHabitLog getDailyHabitLog(Long habitId, LocalDate date) {
        User user = authUtil.getAuthenticatedUser();
        habitRepository.findByIdAndUserId(habitId, user.getId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));

        DailyHabitLog dhl = dailyHabitLogRepository.findByHabitAndDate(habitId, date)
                .orElseThrow(() -> new ApiRequestException(DHL_NOT_FOUND, HttpStatus.NOT_FOUND));

        return dhl;
    }

    public List<DailyHabitLog> getDailyHabitLogsForWeek(Long habitId, Integer year, Integer weekNumber) {
        User user = authUtil.getAuthenticatedUser();
        habitRepository.findByIdAndUserId(habitId, user.getId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate startOfWeek = LocalDate.of(year, 1, 1)
                .with(weekFields.weekOfYear(), weekNumber)
                .with(DayOfWeek.MONDAY);

        LocalDate endOfWeek = startOfWeek.plusDays(6);

        return dailyHabitLogRepository.findDailyHabitLogsForWeek(habitId, startOfWeek, endOfWeek);
    }

}
