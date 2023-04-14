package com.marcus.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcus.dscommerce.dto.CustomError;
import com.marcus.dscommerce.services.exepitions.DatabaseExeption;
import com.marcus.dscommerce.services.exepitions.ResourceNotFoundExeption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundExeption e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    
    }
    @ExceptionHandler(DatabaseExeption.class)
    public ResponseEntity<CustomError> database(DatabaseExeption e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    
    }

}
