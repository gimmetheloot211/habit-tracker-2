package com.habit.habit_tracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.habit_tracker.domain.Task;
import com.habit.habit_tracker.dto.task.TaskCreateRequest;
import com.habit.habit_tracker.dto.task.TaskResponse;
import com.habit.habit_tracker.dto.task.TaskSummaryResponse;
import com.habit.habit_tracker.dto.task.TaskUpdateRequest;
import com.habit.habit_tracker.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/tasks")
public class TaskController {

        private final TaskService taskService;

        public TaskController(TaskService taskService) {
                this.taskService = taskService;
        }

        @PostMapping
        public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {
                Task createdTask = taskService.createTask(taskCreateRequest);
                TaskResponse taskResponse = new TaskResponse(
                                createdTask.getId(),
                                createdTask.getUser().getId(),
                                createdTask.getName(),
                                createdTask.getDescription(),
                                createdTask.getStatus(),
                                createdTask.getDifficulty(),
                                createdTask.getDueDate(),
                                createdTask.getCreatedAt());

                return ResponseEntity
                                .created(URI.create("/api/v1/tasks/" + createdTask.getId()))
                                .body(taskResponse);
        }

        @PatchMapping(path = "/{id}")
        public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id,
                        @Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
                Task updatedTask = taskService.updateTask(id, taskUpdateRequest);
                TaskResponse taskResponse = new TaskResponse(
                                updatedTask.getId(),
                                updatedTask.getUser().getId(),
                                updatedTask.getName(),
                                updatedTask.getDescription(),
                                updatedTask.getStatus(),
                                updatedTask.getDifficulty(),
                                updatedTask.getDueDate(),
                                updatedTask.getCreatedAt());
                return ResponseEntity.ok(taskResponse);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
                Task task = taskService.getTask(id);
                TaskResponse taskResponse = new TaskResponse(
                                task.getId(),
                                task.getUser().getId(),
                                task.getName(),
                                task.getDescription(),
                                task.getStatus(),
                                task.getDifficulty(),
                                task.getDueDate(),
                                task.getCreatedAt());
                return ResponseEntity.ok(taskResponse);
        }

        @GetMapping
        public ResponseEntity<List<TaskSummaryResponse>> getAllTasks() {
                List<Task> tasks = taskService.getAllTasks();
                List<TaskSummaryResponse> tasksResponse = tasks.stream()
                                .map(task -> new TaskSummaryResponse(
                                                task.getId(),
                                                task.getName(),
                                                task.getStatus(),
                                                task.getDifficulty(),
                                                task.getDueDate()))
                                .toList();
                return ResponseEntity.ok(tasksResponse);
        }
}
