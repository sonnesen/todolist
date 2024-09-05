package com.sonnesen.todolist.infrastructure.gateway;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sonnesen.todolist.domain.pagination.Pagination;
import com.sonnesen.todolist.domain.task.entity.Task;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;
import com.sonnesen.todolist.infrastructure.persistence.entity.TaskJPAEntity;
import com.sonnesen.todolist.infrastructure.persistence.repository.TaskJPARepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component("taskGateway")
public class TaskGatewayImpl implements TaskGateway {

    private final TaskJPARepository taskJPARepository;

    @Override
    public Task createTask(final Task task) {
        return taskJPARepository.save(TaskJPAEntity.of(task)).toTask();
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTaskById'");
    }

    @Override
    public Pagination<Task> findAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Task updateTask(Task newTask) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

    @Override
    public void completeTask(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'completeTask'");
    }

    @Override
    public void deleteTask(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
    }

    @Override
    public void reopenTask(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reopenTask'");
    }

}
