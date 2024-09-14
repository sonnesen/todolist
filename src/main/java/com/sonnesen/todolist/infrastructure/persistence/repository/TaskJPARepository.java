package com.sonnesen.todolist.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;

public interface TaskJPARepository extends JpaRepository<TaskJPAEntity, Long> {

}
