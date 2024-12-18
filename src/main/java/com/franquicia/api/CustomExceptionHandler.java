package com.franquicia.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getReason());
        response.put("code", ex.getStatusCode().value());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

}
