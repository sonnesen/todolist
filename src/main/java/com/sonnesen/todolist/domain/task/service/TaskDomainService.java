package com.sonnesen.todolist.domain.task.service;

import com.sonnesen.todolist.domain.exceptions.TaskAlreadyCompletedException;
import com.sonnesen.todolist.domain.exceptions.TaskInactiveException;
import com.sonnesen.todolist.domain.exceptions.TaskNotFoundException;
import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TaskDomainService {

    @NonNull
    private final TaskGateway taskGateway;

    public Task createTask(final Task newTask) {
        final var createdTask = taskGateway.createTask(newTask);

        return createdTask;
    }

    public void completeTask(final Long taskId) {
        final var task = taskGateway.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        isActive(task);

        if (task.isCompleted()) {
            log.error("Task {} is already completed", task.getId());
            throw new TaskAlreadyCompletedException(task.getId());
        }

        task.markAsCompleted();
        taskGateway.completeTask(task);
    }

    private void isActive(final Task task) {
        if (task.isDeleted()) {
            log.error("Task {} is deleted", task.getId());
            throw new TaskInactiveException(task.getId());
        }
    }

}
