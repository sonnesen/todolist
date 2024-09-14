package com.sonnesen.todolist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sonnesen.todolist.infrastructure.mapper.TaskMapper;

@Configuration
public class MapperConfig {

    @Bean
    TaskMapper taskMapper() {
        return new TaskMapper();
    }
}
