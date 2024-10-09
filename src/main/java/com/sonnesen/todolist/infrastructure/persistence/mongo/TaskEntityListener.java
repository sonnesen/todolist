package com.sonnesen.todolist.infrastructure.persistence.mongo;

import java.time.Instant;

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
    public void onBeforeConvert(@NonNull BeforeConvertEvent<TaskJPAEntity> source) {
        if (source.getSource().getId().intValue() < 1) {
            source.getSource().setId(databaseSequenceGeneratorService.generateSequence(TaskJPAEntity.SEQUENCE_NAME));
        }

        if (source.getSource().getCreatedAt() == null) {
            source.getSource().setCreatedAt(Instant.now());
        }
    }

}
