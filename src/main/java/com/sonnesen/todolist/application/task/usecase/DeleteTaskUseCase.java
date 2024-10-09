package com.sonnesen.todolist.application.task.usecase;

import java.time.Instant;

import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTaskUseCase {

    @NonNull
    private final TaskDomainService taskDomainService;

    public void execute(final Long taskId) {
        this.taskDomainService.deleteTaskById(taskId);
    }

    public record Output(Long id, String title, String description, boolean completed, Instant createdAt,
            Instant updatedAt, Instant deletedAt) {

        public static Output from(Task task) {
            return new Output(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(),
                    task.getCreatedAt(),
                    task.getUpdatedAt(),
                    task.getDeletedAt());
        }
    }
}
