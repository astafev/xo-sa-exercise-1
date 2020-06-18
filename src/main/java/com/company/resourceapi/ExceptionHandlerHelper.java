package com.company.resourceapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Collections.singletonMap;

@RestControllerAdvice
public class ExceptionHandlerHelper {
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        return ResponseEntity.badRequest().body(singletonMap("message", "NumberFormatException " + e.getMessage()));
    }
}
