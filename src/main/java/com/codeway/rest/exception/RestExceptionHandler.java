package com.codeway.rest.exception;

import com.codeway.persistence.exception.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    // WebExceptionHandler
    //TODO: Riusciamo ad avere anche la request?
    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<Object> handleUnmappedException(DocumentNotFoundException ex, ServerHttpRequest serverHttpRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
