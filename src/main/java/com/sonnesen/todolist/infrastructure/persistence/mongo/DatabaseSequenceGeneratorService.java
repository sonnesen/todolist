package com.sonnesen.todolist.infrastructure.persistence.mongo;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

public class DatabaseSequenceGeneratorService {

    private final MongoOperations mongoOperations;

    public DatabaseSequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long generateSequence(String sequenceName) {
        final DatabaseSequence databaseSequence = mongoOperations.findAndModify(
                query(where("_id").is(sequenceName)),
                new Update().inc("value", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(databaseSequence) ? databaseSequence.getValue() : 1;
    }
}
