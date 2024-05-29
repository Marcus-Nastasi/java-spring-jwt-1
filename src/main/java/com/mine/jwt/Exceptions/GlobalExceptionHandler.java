package com.mine.jwt.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable throwable) {
        String errorMessage = "{\"error\": " + "\"" + throwable.getMessage() + "\"" + '}';
        logger.error(errorMessage, throwable);
        return ResponseEntity.badRequest().body(errorMessage);
    }
}


