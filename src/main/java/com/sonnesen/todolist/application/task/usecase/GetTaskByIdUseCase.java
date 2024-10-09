package com.sonnesen.todolist.application.task.usecase;

import java.time.Instant;

import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTaskByIdUseCase {

    @NonNull
    private final TaskDomainService taskDomainService;

    public Output execute(final String taskId) {
        final var task = taskDomainService.getTaskById(taskId);
        return Output.from(task);
    }

    public record Output(String id, String title, String description, boolean completed, Instant createdAt,
            Instant updatedAt, Instant deletedAt) {

        public static Output from(final Task task) {
            return new Output(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(),
                    task.getCreatedAt(),
                    task.getUpdatedAt(),
                    task.getDeletedAt());
        }
    }

}
