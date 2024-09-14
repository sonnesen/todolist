package com.sonnesen.todolist.domain.task.event;

import java.time.Instant;

import com.sonnesen.todolist.domain.DomainEvent;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskReopenedEvent implements DomainEvent {

    private final Long taskId;
    private Instant occurredOn;

    public TaskReopenedEvent(final Long taskId) {
        this.taskId = taskId;
        this.occurredOn = Instant.now();
    }
}
