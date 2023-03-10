package com.example.smev.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class SMEVExceptionHandler {
    @ExceptionHandler(WorkerException.class)
    protected ResponseEntity<Object> workerException() {
        log.error("Worker Exception");
        return ResponseEntity.badRequest().body("Something happened in worker");
    }
}
