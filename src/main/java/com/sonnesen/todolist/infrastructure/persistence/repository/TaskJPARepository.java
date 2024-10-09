package com.sonnesen.todolist.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;

public interface TaskJPARepository extends MongoRepository<TaskJPAEntity, Long> {

}
