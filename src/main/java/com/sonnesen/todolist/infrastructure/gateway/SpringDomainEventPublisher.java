package com.sonnesen.todolist.infrastructure.gateway;

import org.springframework.context.ApplicationEventPublisher;

import com.sonnesen.todolist.domain.DomainEventPublisher;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {

    @NonNull
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Object event) {
        log.info("Publishing event: {}", event);
        applicationEventPublisher.publishEvent(event);
    }

}
