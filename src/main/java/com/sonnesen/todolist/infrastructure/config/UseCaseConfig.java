package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.application.tasks.usecase.CreateTaskUseCase;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    @NonNull
    private final TaskDomainService taskDomainService;

    @Bean
    public CreateTaskUseCase createTaskUseCase() {
        return new CreateTaskUseCase(taskDomainService);
    }
}
