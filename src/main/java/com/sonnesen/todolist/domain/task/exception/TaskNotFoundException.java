package com.sonnesen.todolist.domain.task.exception;

import com.sonnesen.todolist.domain.DomainException;

public class TaskNotFoundException extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String TASK_WITH_ID_D_NOT_FOUND = "Task with id %d not found";
    private static final String TASK_NOT_FOUND = "Task not found";

    public TaskNotFoundException(final Long taskId) {
        super(TASK_NOT_FOUND, String.format(TASK_WITH_ID_D_NOT_FOUND, taskId), HTTP_STATUS_NOT_FOUND);
    }

}
