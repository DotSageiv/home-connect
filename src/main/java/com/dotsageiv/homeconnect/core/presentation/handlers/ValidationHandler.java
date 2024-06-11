package com.dotsageiv.homeconnect.core.presentation.handlers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ArrayList<String>> constraintViolation(ConstraintViolationException ex) {
        var errorMessages = new ArrayList<String>();

        ex.getConstraintViolations()
                .forEach(violation ->
                        errorMessages.add(violation.getMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessages);
    }
}