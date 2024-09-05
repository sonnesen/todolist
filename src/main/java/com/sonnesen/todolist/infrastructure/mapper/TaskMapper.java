package com.sonnesen.todolist.infrastructure.mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.sonnesen.todolist.application.dto.CreateTaskDTO;
import com.sonnesen.todolist.application.dto.TaskDTO;
import com.sonnesen.todolist.application.tasks.usecase.CreateTaskUseCase;

@Component
public class TaskMapper {

    public TaskDTO toDTO(final CreateTaskUseCase.Output output) {
        return new TaskDTO().id(output.id()).title(output.title()).description(output.description())
                .completed(output.completed()).createdAt(getOffsetDateTimeSecurely(output.createdAt()))
                .updatedAt(getOffsetDateTimeSecurely(output.updatedAt()))
                .deletedAt(getOffsetDateTimeSecurely(output.deletedAt()));
    }

    public CreateTaskUseCase.Input from(final CreateTaskDTO dto) {
        return new CreateTaskUseCase.Input(dto.getTitle(), dto.getDescription());
    }

    private OffsetDateTime getOffsetDateTimeSecurely(final Instant instant) {
        if (instant == null)
            return null;
        return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
