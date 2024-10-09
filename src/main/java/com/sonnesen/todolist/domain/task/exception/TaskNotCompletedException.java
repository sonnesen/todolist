package com.sonnesen.todolist.domain.task.exception;

import com.sonnesen.todolist.domain.DomainException;

public class TaskNotCompletedException extends DomainException {

    private static final int HTTP_STATUS_CONFLICT = 409;
    private static final String TASK_WITH_ID_D_IS_NOT_COMPLETED = "Task with id %s is not completed";
    private static final String TASK_NOT_COMPLETED = "Task not completed";

    public TaskNotCompletedException(final String taskId) {
        super(TASK_NOT_COMPLETED, String.format(TASK_WITH_ID_D_IS_NOT_COMPLETED, taskId), HTTP_STATUS_CONFLICT);
    }

}
