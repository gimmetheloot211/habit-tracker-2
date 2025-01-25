package com.habit.habit_tracker.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.Habit;
import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.habit.HabitCreateRequest;
import com.habit.habit_tracker.dto.habit.HabitUpdateRequest;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.HabitRepository;
import com.habit.habit_tracker.repository.UserRepository;
import com.habit.habit_tracker.security.AuthUtil;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    public HabitService(HabitRepository habitRepository, AuthUtil authUtil, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.authUtil = authUtil;
        this.userRepository = userRepository;
    }

    public Habit createHabit(HabitCreateRequest habitCreateRequest) {
        User user = authUtil.getAuthenticatedUser();

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));

        Habit habit = new Habit();

        habit.setName(habitCreateRequest.getName());
        if (habitCreateRequest.getDescription() != null) {
            habit.setDescription(habitCreateRequest.getDescription());
        }
        habit.setUser(user);
        habit.setMinutesTotal(0);

        return habitRepository.save(habit);
    }

    public Habit updateHabit(Long id, HabitUpdateRequest habitUpdateRequest) {
        User user = authUtil.getAuthenticatedUser();
        Habit habit = habitRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (habitUpdateRequest.getName() != null) {
            habit.setName(habitUpdateRequest.getName());
        }

        if (habitUpdateRequest.getDescription() != null) {
            habit.setDescription(habitUpdateRequest.getDescription());
        }

        return habitRepository.save(habit);

    }

    public Habit getHabit(Long id) {
        User user = authUtil.getAuthenticatedUser();
        return habitRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ApiRequestException(HABIT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public List<Habit> getAllHabits() {
        User user = authUtil.getAuthenticatedUser();
        return habitRepository.findAllByUserId(user.getId());
    }
}
