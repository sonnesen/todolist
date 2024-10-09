package com.sonnesen.todolist.domain.task.entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Task(final String title, final String description) {
        this.title = title;
        this.description = description;
    }

    private Task(final Long id, final String title, final String description) {
        this(title, description);
        this.id = id;
    }

    public static Task with(final String title, final String description) {
        return new Task(title, description);
    }

    public static Task with(final Long id, final String title, final String description) {
        return new Task(id, title, description);
    }

    public static Task newTask(final String title, final String description) {
        return new Task(title, description);
    }

    public Task markAsCompleted() {
        this.completed = true;
        return this;
    }

    public Task markAsNotCompleted() {
        this.completed = false;
        return this;
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    public static Task with(final Long id, final String title, final String description, final boolean completed,
            final Instant createdAt,
            final Instant updatedAt, final Instant deletedAt) {
        return new Task(id, title, description, completed, createdAt, updatedAt, deletedAt);
    }

}
