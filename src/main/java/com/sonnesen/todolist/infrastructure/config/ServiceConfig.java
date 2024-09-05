package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.domain.task.gateway.TaskGateway;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    @NonNull
    private final TaskGateway taskGateway;

    @Bean
    public TaskDomainService taskDomainService() {
        return new TaskDomainService(taskGateway);
    }
}
