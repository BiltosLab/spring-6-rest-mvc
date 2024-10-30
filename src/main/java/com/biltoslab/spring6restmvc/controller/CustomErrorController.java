package com.biltoslab.spring6restmvc.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorController {

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
