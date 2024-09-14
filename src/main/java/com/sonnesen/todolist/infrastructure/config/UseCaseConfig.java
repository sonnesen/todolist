package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.application.task.usecase.CompleteTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.CreateTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.DeleteTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.GetAllTasksUseCase;
import com.sonnesen.todolist.application.task.usecase.GetTaskByIdUseCase;
import com.sonnesen.todolist.application.task.usecase.ReopenTaskUseCase;
import com.sonnesen.todolist.application.task.usecase.UpdateTaskUseCase;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

@Configuration
public class UseCaseConfig {

    @Bean
    CompleteTaskUseCase completeTaskUseCase(final TaskDomainService taskDomainService) {
        return new CompleteTaskUseCase(taskDomainService);
    }

    @Bean
    CreateTaskUseCase createTaskUseCase(final TaskDomainService taskDomainService) {
        return new CreateTaskUseCase(taskDomainService);
    }

    @Bean
    DeleteTaskUseCase deleteTaskUseCase(final TaskDomainService taskDomainService) {
        return new DeleteTaskUseCase(taskDomainService);
    }

    @Bean
    GetAllTasksUseCase getAllTasksUseCase(final TaskDomainService taskDomainService) {
        return new GetAllTasksUseCase(taskDomainService);
    }

    @Bean
    GetTaskByIdUseCase getTaskByIdUseCase(final TaskDomainService taskDomainService) {
        return new GetTaskByIdUseCase(taskDomainService);
    }

    @Bean
    ReopenTaskUseCase reopenTaskUseCase(final TaskDomainService taskDomainService) {
        return new ReopenTaskUseCase(taskDomainService);
    }

    @Bean
    UpdateTaskUseCase updateTaskUseCase(final TaskDomainService taskDomainService) {
        return new UpdateTaskUseCase(taskDomainService);
    }

}
