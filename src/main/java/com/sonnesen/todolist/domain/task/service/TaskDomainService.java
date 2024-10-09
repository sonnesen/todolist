package com.sonnesen.todolist.domain.task.service;

import java.time.Instant;

import com.sonnesen.todolist.domain.DomainEventPublisher;
import com.sonnesen.todolist.domain.pagination.Pagination;
import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.event.TaskCompletedEvent;
import com.sonnesen.todolist.domain.task.event.TaskDeletedEvent;
import com.sonnesen.todolist.domain.task.event.TaskReopenedEvent;
import com.sonnesen.todolist.domain.task.event.TaskUpdatedEvent;
import com.sonnesen.todolist.domain.task.exception.TaskAlreadyCompletedException;
import com.sonnesen.todolist.domain.task.exception.TaskInactiveException;
import com.sonnesen.todolist.domain.task.exception.TaskNotCompletedException;
import com.sonnesen.todolist.domain.task.exception.TaskNotFoundException;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TaskDomainService {

    @NonNull
    private final TaskGateway taskGateway;

    @NonNull
    private final DomainEventPublisher domainEventPublisher;

    public void completeTask(final String taskId) {
        final var task = taskGateway.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        isActive(task);

        if (task.isCompleted()) {
            log.error("Task {} is already completed", task.getId());
            throw new TaskAlreadyCompletedException(task.getId());
        }

        task.markAsCompleted();
        taskGateway.completeTask(task);

        final var event = new TaskCompletedEvent(task.getId());
        domainEventPublisher.publish(event);
    }

    public void reopenTask(final String taskId) {
        final var task = taskGateway.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        isActive(task);

        if (!task.isCompleted()) {
            log.error("Task {} is not completed", task.getId());
            throw new TaskNotCompletedException(task.getId());
        }

        task.markAsNotCompleted();
        taskGateway.reopenTask(task);

        final var event = new TaskReopenedEvent(task.getId());
        domainEventPublisher.publish(event);
    }

    public Task createTask(final Task newTask) {
        final var createdTask = taskGateway.createTask(newTask);

        final var event = new TaskCompletedEvent(createdTask.getId());
        domainEventPublisher.publish(event);

        return createdTask;
    }

    public Task updateTask(final Task taskToUpdate) {
        final var existingTask = taskGateway.getTaskById(taskToUpdate.getId())
                .orElseThrow(() -> new TaskNotFoundException(taskToUpdate.getId()));

        isActive(existingTask);

        taskToUpdate.setCreatedAt(existingTask.getCreatedAt());
        taskToUpdate.setUpdatedAt(existingTask.getUpdatedAt());
        taskToUpdate.setDeletedAt(existingTask.getDeletedAt());
        taskToUpdate.setCompleted(existingTask.isCompleted());

        final var updatedTask = taskGateway.updateTask(taskToUpdate);

        final var event = new TaskUpdatedEvent(updatedTask.getId());
        domainEventPublisher.publish(event);

        return updatedTask;
    }

    public void deleteTaskById(final String id) {
        final var task = taskGateway.getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (task.getDeletedAt() == null) {
            task.setDeletedAt(Instant.now());
            taskGateway.deleteTask(task);

            final var event = new TaskDeletedEvent(task.getId());
            domainEventPublisher.publish(event);
        }
    }

    public Task getTaskById(final String taskId) {
        return taskGateway.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Pagination<Task> listAllTasks(int page, int size) {
        return taskGateway.findAll(page, size);
    }

    private void isActive(final Task task) {
        if (task.isDeleted()) {
            log.error("Task {} is deleted", task.getId());
            throw new TaskInactiveException(task.getId());
        }
    }

}
