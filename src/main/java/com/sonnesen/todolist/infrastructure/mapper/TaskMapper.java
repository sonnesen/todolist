package com.sonnesen.todolist.infrastructure.mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import com.sonnesen.todolist.application.dto.CreateTaskDTO;
import com.sonnesen.todolist.application.dto.PaginationDTO;
import com.sonnesen.todolist.application.dto.TaskDTO;
import com.sonnesen.todolist.application.dto.TasksResponseDTO;
import com.sonnesen.todolist.application.dto.UpdateTaskDTO;
import com.sonnesen.todolist.application.task.usecase.CreateTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.GetAllTasksUseCase;
import com.sonnesen.todolist.application.task.usecase.GetTaskByIdUseCase;
import com.sonnesen.todolist.application.task.usecase.UpdateTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.UpdateTaskUseCase.Output;
import com.sonnesen.todolist.domain.pagination.Pagination;

public class TaskMapper {

    public TaskDTO toDTO(final GetTaskByIdUseCase.Output output) {
        return new TaskDTO().id(output.id()).title(output.title()).description(output.description())
                .completed(output.completed()).createdAt(getOffsetDateTimeSecurely(output.createdAt()))
                .updatedAt(getOffsetDateTimeSecurely(output.updatedAt()))
                .deletedAt(getOffsetDateTimeSecurely(output.deletedAt()));
    }

    public TaskDTO toDTO(final CreateTaskUseCase.Output output) {
        return new TaskDTO().id(output.id()).title(output.title()).description(output.description())
                .completed(output.completed()).createdAt(getOffsetDateTimeSecurely(output.createdAt()))
                .updatedAt(getOffsetDateTimeSecurely(output.updatedAt()))
                .deletedAt(getOffsetDateTimeSecurely(output.deletedAt()));
    }

    public TasksResponseDTO toDTO(final Pagination<GetAllTasksUseCase.Output> pagination) {
        final var paginationDTO = new PaginationDTO().page(pagination.page()).size(pagination.size())
                .total(pagination.total()).totalPages(pagination.totalPages());

        return new TasksResponseDTO().data(pagination.items().stream().map(this::toDTO).toList())
                .pagination(paginationDTO);
    }

    public TaskDTO toDTO(final Output updatedTask) {
        return new TaskDTO().id(updatedTask.id()).title(updatedTask.title()).description(updatedTask.description())
                .completed(updatedTask.completed()).createdAt(getOffsetDateTimeSecurely(updatedTask.createdAt()))
                .updatedAt(getOffsetDateTimeSecurely(updatedTask.updatedAt()))
                .deletedAt(getOffsetDateTimeSecurely(updatedTask.deletedAt()));
    }

    public TaskDTO toDTO(final GetAllTasksUseCase.Output output) {
        return new TaskDTO().id(output.id()).title(output.title()).description(output.description())
                .completed(output.completed()).createdAt(getOffsetDateTimeSecurely(output.createdAt()))
                .updatedAt(getOffsetDateTimeSecurely(output.updatedAt()))
                .deletedAt(getOffsetDateTimeSecurely(output.deletedAt()));
    }

    public CreateTaskUseCase.Input from(final CreateTaskDTO dto) {
        return new CreateTaskUseCase.Input(dto.getTitle(), dto.getDescription());
    }

    public UpdateTaskUseCase.Input from(final Long id, final UpdateTaskDTO task) {
        return new UpdateTaskUseCase.Input(id, task.getTitle(), task.getDescription());
    }

    private OffsetDateTime getOffsetDateTimeSecurely(final Instant instant) {
        if (instant == null)
            return null;
        return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
