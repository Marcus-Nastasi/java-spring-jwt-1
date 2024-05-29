package com.mine.jwt.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable throwable) {
        return new ResponseEntity<>(
        "{\"error\": " + "\"" + throwable.getMessage() + "\"" + '}',
                HttpStatus.BAD_REQUEST
        );
    }
}

