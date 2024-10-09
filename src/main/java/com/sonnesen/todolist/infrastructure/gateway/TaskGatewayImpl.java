package com.sonnesen.todolist.infrastructure.gateway;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sonnesen.todolist.domain.pagination.Pagination;
import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;
import com.sonnesen.todolist.infrastructure.persistence.entity.task.TaskJPAEntity;
import com.sonnesen.todolist.infrastructure.persistence.repository.TaskJPARepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskGatewayImpl implements TaskGateway {

    private final TaskJPARepository taskJPARepository;

    @Override
    public Task createTask(final Task task) {
        Instant now = Instant.now();
        task.setUpdatedAt(now);
        return taskJPARepository.save(TaskJPAEntity.of(task)).toTask();
    }

    @Override
    public Optional<Task> getTaskById(final Long taskId) {
        return taskJPARepository.findById(taskId).map(TaskJPAEntity::toTask);
    }

    @Override
    public Pagination<Task> findAll(final int page, final int size) {
        final var pageable = Pageable.ofSize(size).withPage(page);
        Page<Task> taskList = taskJPARepository.findAll(pageable).map(TaskJPAEntity::toTask);
        return Pagination.from(taskList.getNumber(), taskList.getSize(), (int) taskList.getTotalElements(),
                taskList.getTotalPages(), taskList.getContent());
    }

    @Override
    public Task updateTask(final Task task) {
        task.setUpdatedAt(Instant.now());
        return taskJPARepository.save(TaskJPAEntity.of(task)).toTask();
    }

    @Override
    public void completeTask(final Task task) {
        task.setUpdatedAt(Instant.now());
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

    @Override
    public void deleteTask(final Task task) {
        if (task.getDeletedAt() == null) {
            task.setDeletedAt(Instant.now());
        }
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

    @Override
    public void reopenTask(final Task task) {
        task.setUpdatedAt(Instant.now());
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

}
