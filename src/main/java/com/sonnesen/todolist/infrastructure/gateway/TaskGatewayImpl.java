package com.sonnesen.todolist.infrastructure.gateway;

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
    public Task updateTask(final Task newTask) {
        return taskJPARepository.save(TaskJPAEntity.of(newTask)).toTask();
    }

    @Override
    public void completeTask(final Task task) {
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

    @Override
    public void deleteTask(final Task task) {
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

    @Override
    public void reopenTask(final Task task) {
        taskJPARepository.save(TaskJPAEntity.of(task));
    }

}
