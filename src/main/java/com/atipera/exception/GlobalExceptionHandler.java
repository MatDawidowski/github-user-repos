package com.atipera.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GitHubUserNotFoundException.class)
    public ResponseEntity<?> handleNotFound(GitHubUserNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "status", 404,
                "message", ex.getMessage()
        ));
    }
}
