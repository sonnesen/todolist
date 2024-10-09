package com.sonnesen.todolist.infrastructure.persistence.mongo;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.lang.NonNull;

import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;

public class TaskEntityListener extends AbstractMongoEventListener<TaskJPAEntity> {

    private final DatabaseSequenceGeneratorService databaseSequenceGeneratorService;

    public TaskEntityListener(DatabaseSequenceGeneratorService databaseSequenceGeneratorService) {
        this.databaseSequenceGeneratorService = databaseSequenceGeneratorService;
    }

    @Override
    public void onBeforeConvert(@NonNull final BeforeConvertEvent<TaskJPAEntity> source) {
        final Long taskId = Optional.ofNullable(source.getSource().getId()).orElse(Long.valueOf(0));

        if (taskId < 1) {
            source.getSource().setId(databaseSequenceGeneratorService.generateSequence(TaskJPAEntity.SEQUENCE_NAME));
        }

        final Instant now = Instant.now();

        if (source.getSource().getCreatedAt() == null) {
            source.getSource().setCreatedAt(now);
        }

        source.getSource().setUpdatedAt(now);
    }

}
