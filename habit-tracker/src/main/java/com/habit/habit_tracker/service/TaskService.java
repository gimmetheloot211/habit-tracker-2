package com.habit.habit_tracker.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.habit.habit_tracker.domain.Task;
import com.habit.habit_tracker.domain.User;
import com.habit.habit_tracker.dto.task.TaskCreateRequest;
import com.habit.habit_tracker.dto.task.TaskUpdateRequest;
import com.habit.habit_tracker.exception.ApiRequestException;
import com.habit.habit_tracker.repository.TaskRepository;
import com.habit.habit_tracker.repository.UserRepository;
import com.habit.habit_tracker.security.AuthUtil;

import jakarta.transaction.Transactional;

import static com.habit.habit_tracker.constants.ErrorMessage.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, AuthUtil authUtil) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.authUtil = authUtil;
    }

    @Transactional
    public Task createTask(TaskCreateRequest taskCreateRequest) {
        User user = authUtil.getAuthenticatedUser();

        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));

        Task task = new Task();
        task.setName(taskCreateRequest.getName());
        task.setDescription(taskCreateRequest.getDescription());
        task.setUser(user);
        task.setDifficulty(taskCreateRequest.getDifficulty());
        task.setDueDate(taskCreateRequest.getDueDate());

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long taskId, TaskUpdateRequest taskUpdateRequest) {
        User user = authUtil.getAuthenticatedUser();
        Task task = taskRepository.findByIdAndUserId(taskId, user.getId())
                .orElseThrow(() -> new ApiRequestException(TASK_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (taskUpdateRequest.getName() != null) {
            task.setName(taskUpdateRequest.getName());
        }
        if (taskUpdateRequest.getDescription() != null) {
            task.setDescription(taskUpdateRequest.getDescription());
        }
        if (taskUpdateRequest.getDifficulty() != null) {
            task.setDifficulty(taskUpdateRequest.getDifficulty());
        }
        if (taskUpdateRequest.getStatus() != null) {
            task.setStatus(taskUpdateRequest.getStatus());
        }

        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        User user = authUtil.getAuthenticatedUser();
        return taskRepository.findByIdAndUserId(taskId, user.getId())
                .orElseThrow(() -> new ApiRequestException(TASK_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public List<Task> getAllTasks() {
        User user = authUtil.getAuthenticatedUser();
        return taskRepository.findAllByUserId(user.getId());
    }
}
