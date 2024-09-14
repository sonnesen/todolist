package com.sonnesen.todolist.infrastructure.event;

import org.springframework.context.event.EventListener;

import com.sonnesen.todolist.domain.task.event.TaskCompletedEvent;
import com.sonnesen.todolist.domain.task.event.TaskCreatedEvent;
import com.sonnesen.todolist.domain.task.event.TaskDeletedEvent;
import com.sonnesen.todolist.domain.task.event.TaskReopenedEvent;
import com.sonnesen.todolist.domain.task.event.TaskUpdatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskEventListener {

    @EventListener
    public void onTaskCompleted(final TaskCompletedEvent event) {
        log.info("Task completed: {} at {}", event.getTaskId(), event.getOccurredOn());
    }

    @EventListener
    public void onTaskReopened(final TaskReopenedEvent event) {
        log.info("Task reopened: {} at {}", event.getTaskId(), event.getOccurredOn());
    }

    @EventListener
    public void onTaskCreated(final TaskCreatedEvent event) {
        log.info("Task created: {} at {}", event.getTaskId(), event.getOccurredOn());
    }

    @EventListener
    public void onTaskUpdated(final TaskUpdatedEvent event) {
        log.info("Task updated: {} at {}", event.getTaskId(), event.getOccurredOn());
    }

    @EventListener
    public void onTaskDeleted(final TaskDeletedEvent event) {
        log.info("Task deleted: {} at {}", event.getTaskId(), event.getOccurredOn());
    }
}
