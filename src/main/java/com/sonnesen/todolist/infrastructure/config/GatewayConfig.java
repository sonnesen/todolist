package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.domain.DomainEventPublisher;
import com.sonnesen.todolist.domain.task.gateway.TaskGateway;
import com.sonnesen.todolist.infrastructure.event.TaskEventListener;
import com.sonnesen.todolist.infrastructure.gateway.SpringDomainEventPublisher;
import com.sonnesen.todolist.infrastructure.gateway.TaskGatewayImpl;
import com.sonnesen.todolist.infrastructure.persistence.repository.TaskJPARepository;

@Configuration
public class GatewayConfig {

    @Bean
    TaskGateway taskGateway(final TaskJPARepository taskJPARepository) {
        return new TaskGatewayImpl(taskJPARepository);
    }

    @Bean
    DomainEventPublisher domainEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        return new SpringDomainEventPublisher(applicationEventPublisher);
    }

    @Bean
    TaskEventListener taskEventListener() {
        return new TaskEventListener();
    }

}
