package com.sonnesen.todolist.application.tasks.usecase;

import java.time.Instant;

import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskUseCase {

    @NonNull
    private final TaskDomainService taskDomainService;

    public Output execute(Input input) {
        final var task = Task.newTask(input.title(), input.description());
        final var createdTask = taskDomainService.createTask(task);
        return Output.from(createdTask);
    }

    public record Input(String title, String description) {
    }

    public record Output(Long id, String title, String description, boolean completed, Instant createdAt,
            Instant updatedAt, Instant deletedAt) {

        public static Output from(Task task) {
            return new Output(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(),
                    task.getCreatedAt(),
                    task.getUpdatedAt(), task.getDeletedAt());
        }
    }
}
