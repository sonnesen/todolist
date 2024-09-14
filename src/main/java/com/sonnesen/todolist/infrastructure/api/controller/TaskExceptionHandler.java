package com.sonnesen.todolist.infrastructure.api.controller;

import java.time.Instant;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sonnesen.todolist.domain.DomainException;

@RestControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        final var problemDetail = ProblemDetail.forStatus(ex.getStatus());
        problemDetail.setTitle(ex.getTitle());
        problemDetail.setDetail(ex.getDetail());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
