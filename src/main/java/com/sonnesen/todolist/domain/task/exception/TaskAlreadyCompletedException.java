package com.sonnesen.todolist.domain.task.exception;

import com.sonnesen.todolist.domain.DomainException;

public class TaskAlreadyCompletedException extends DomainException {

    private static final int HTTP_STATUS_CONFLICT = 409;
    private static final String TASK_WITH_ID_D_IS_ALREADY_COMPLETED = "Task with id %s is already completed";
    private static final String TASK_ALREADY_COMPLETED = "Task already completed";

    public TaskAlreadyCompletedException(final Long taskId) {
        super(TASK_ALREADY_COMPLETED, String.format(TASK_WITH_ID_D_IS_ALREADY_COMPLETED, taskId), HTTP_STATUS_CONFLICT);
    }

}
