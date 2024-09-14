package com.sonnesen.todolist.domain;

public interface DomainEventPublisher {

    void publish(final Object event);
}
