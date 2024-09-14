package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.domain.DomainEventPublisher;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;
import com.sonnesen.todolist.domain.task.service.TaskDomainService;

@Configuration
public class ServiceConfig {

    @Bean
    TaskDomainService taskDomainService(final TaskGateway taskGateway,
            final DomainEventPublisher domainEventPublisher) {
        return new TaskDomainService(taskGateway, domainEventPublisher);
    }

}
