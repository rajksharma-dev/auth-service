package com.allactivityplanner.authservice.controller.common;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleExpiredJwt(
            Exception ex,
            HttpServletRequest request) {

        Map<String,Object> error = new HashMap<>();

        error.put("timestamp", Instant.now());
        error.put("status", 503);
        error.put("error", "Service Error");
        error.put("message", "Service Error: some problem  in backend service, please check logs for more details");
        error.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
}
