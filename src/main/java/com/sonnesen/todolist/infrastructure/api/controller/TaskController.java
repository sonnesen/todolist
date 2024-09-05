package com.sonnesen.todolist.infrastructure.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sonnesen.todolist.api.controller.TasksApi;
import com.sonnesen.todolist.application.dto.CreateTaskDTO;
import com.sonnesen.todolist.application.dto.TaskDTO;
import com.sonnesen.todolist.application.tasks.usecase.CreateTaskUseCase;
import com.sonnesen.todolist.infrastructure.mapper.TaskMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    @NonNull
    private final CreateTaskUseCase createTaskUseCase;
    @NonNull
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<TaskDTO> createTask(final CreateTaskDTO task) {
        final var createdTask = createTaskUseCase.execute(taskMapper.from(task));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toDTO(createdTask));
    }
}
