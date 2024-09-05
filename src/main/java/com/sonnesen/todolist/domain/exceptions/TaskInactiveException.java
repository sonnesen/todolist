package com.sonnesen.todolist.domain.exceptions;

import com.sonnesen.todolist.domain.DomainException;

public class TaskInactiveException extends DomainException {

    private static final int HTTP_STATUS_CONFLICT = 409;
    private static final String TASK_WITH_ID_D_IS_INACTIVE = "Task with id %d is inactive";
    private static final String TASK_INACTIVE = "Task inactive";

    public TaskInactiveException(final Long taskId) {
        super(TASK_INACTIVE, TASK_WITH_ID_D_IS_INACTIVE, HTTP_STATUS_CONFLICT);
    }
}
