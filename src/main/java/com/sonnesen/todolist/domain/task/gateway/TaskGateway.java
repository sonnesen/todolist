package com.sonnesen.todolist.domain.task.gateway;

import java.util.Optional;

import com.sonnesen.todolist.domain.pagination.Pagination;
import com.sonnesen.todolist.domain.task.entity.Task;

public interface TaskGateway {

    Task createTask(Task task);

    Optional<Task> getTaskById(Long taskId);

    Pagination<Task> findAll(int page, int size);

    Task updateTask(Task newTask);

    void completeTask(Task task);

    void deleteTask(Task task);

    void reopenTask(Task task);
}
