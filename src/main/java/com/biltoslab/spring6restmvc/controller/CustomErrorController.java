package com.biltoslab.spring6restmvc.controller;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomErrorController {
    private final View error;

    public CustomErrorController(View error) {
        this.error = error;
    }

    @ExceptionHandler(TransactionSystemException.class)
    ResponseEntity handleJPAViolations(TransactionSystemException exception){
        ResponseEntity.BodyBuilder response = ResponseEntity.badRequest();
        if (exception.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ve = (ConstraintViolationException) exception.getCause().getCause();

            List<Map<String, String>> errors = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                    return map;
                    }).toList();


            return response.body(errors);
        }

        return response.build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindError(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errorlist = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return map;
                }).toList();

            return ResponseEntity.badRequest().body(errorlist);
    }
}
