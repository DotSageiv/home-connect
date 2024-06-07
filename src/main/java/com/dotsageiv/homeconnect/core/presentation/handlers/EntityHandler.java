package com.dotsageiv.homeconnect.core.presentation.handlers;

import com.dotsageiv.homeconnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityHandler {
    @ExceptionHandler(EntityNotFoundNotification.class)
    public ResponseEntity<String> entityNotFound(EntityNotFoundNotification ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}