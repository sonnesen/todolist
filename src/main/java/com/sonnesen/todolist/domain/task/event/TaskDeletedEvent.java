package com.sonnesen.todolist.domain.task.event;

import java.time.Instant;

import com.sonnesen.todolist.domain.DomainEvent;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskDeletedEvent implements DomainEvent {

    private final String taskId;
    private Instant occurredOn;

    public TaskDeletedEvent(final String taskId) {
        this.taskId = taskId;
        this.occurredOn = Instant.now();
    }
}
