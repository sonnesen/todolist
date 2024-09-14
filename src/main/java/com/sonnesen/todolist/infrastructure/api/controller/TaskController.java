package com.sonnesen.todolist.infrastructure.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sonnesen.todolist.api.controller.TasksApi;
import com.sonnesen.todolist.application.dto.CreateTaskDTO;
import com.sonnesen.todolist.application.dto.TaskDTO;
import com.sonnesen.todolist.application.dto.TasksResponseDTO;
import com.sonnesen.todolist.application.dto.UpdateTaskDTO;
import com.sonnesen.todolist.application.task.usecase.CompleteTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.CreateTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.DeleteTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.GetAllTasksUseCase;
import com.sonnesen.todolist.application.task.usecase.GetTaskByIdUseCase;
import com.sonnesen.todolist.application.task.usecase.ReopenTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.UpdateTaskUseCase;
import com.sonnesen.todolist.infrastructure.mapper.TaskMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    @NonNull
    private final GetAllTasksUseCase getAllTasksUseCase;
    @NonNull
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    @NonNull
    private final CreateTaskUseCase createTaskUseCase;
    @NonNull
    private final UpdateTaskUseCase updateTaskUseCase;
    @NonNull
    private final CompleteTaskUseCase completeTaskUseCase;
    @NonNull
    private final ReopenTaskUseCase reopenTaskUseCase;
    @NonNull
    private final DeleteTaskUseCase deleteTaskUseCase;
    @NonNull
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<TasksResponseDTO> getAllTasks(final Integer page, final Integer size) {
        final var tasks = getAllTasksUseCase.execute(page, size);
        final var tasksResponse = taskMapper.toDTO(tasks);
        return ResponseEntity.ok(tasksResponse);
    }

    @Override
    public ResponseEntity<TaskDTO> getTaskById(final Long id) {
        final var task = getTaskByIdUseCase.execute(id);
        final var taskDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(taskDTO);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(final CreateTaskDTO task) {
        final var createdTask = createTaskUseCase.execute(taskMapper.from(task));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toDTO(createdTask));
    }

    @Override
    public ResponseEntity<TaskDTO> updateTaskById(final Long id, final UpdateTaskDTO task) {
        final var updatedTask = updateTaskUseCase.execute(taskMapper.from(id, task));
        return ResponseEntity.ok(taskMapper.toDTO(updatedTask));
    }

    @Override
    public ResponseEntity<Void> deleteTaskById(final Long id) {
        deleteTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> completeTaskById(final Long id) {
        completeTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> reopenTaskById(final Long id) {
        reopenTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
