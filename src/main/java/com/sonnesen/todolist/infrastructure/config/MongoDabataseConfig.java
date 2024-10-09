package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import com.sonnesen.todolist.infrastructure.persistence.mongo.DatabaseSequenceGeneratorService;
import com.sonnesen.todolist.infrastructure.persistence.mongo.TaskEntityListener;

@Configuration
public class MongoDabataseConfig {

    @Bean
    DatabaseSequenceGeneratorService databaseSequenceGeneratorService(final MongoOperations mongoOperations) {
        return new DatabaseSequenceGeneratorService(mongoOperations);
    }

    @Bean
    TaskEntityListener taskEntityListener(final DatabaseSequenceGeneratorService databaseSequenceGeneratorService) {
        return new TaskEntityListener(databaseSequenceGeneratorService);
    }

}
