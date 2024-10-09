package com.sonnesen.todolist.application.task.usecase;

import java.time.Instant;

import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTaskUseCase {

    @NonNull
    private final TaskDomainService taskDomainService;

    public Output execute(Input input) {
        final var task = taskDomainService.updateTask(Task.with(input.id(), input.title(), input.description()));
        return Output.from(task);
    }

    public record Input(String id, String title, String description) {

    }

    public record Output(String id, String title, String description, boolean completed, Instant createdAt,
            Instant updatedAt, Instant deletedAt) {

        public static Output from(Task task) {
            return new Output(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(),
                    task.getCreatedAt(),
                    task.getUpdatedAt(),
                    task.getDeletedAt());
        }
    }
}
