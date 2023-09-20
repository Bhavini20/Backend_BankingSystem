package com.example.banking_project_group2.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BalanceExceptions.class)
    public ResponseEntity<?> handleBalanceExceptions(BalanceExceptions ex) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
}
