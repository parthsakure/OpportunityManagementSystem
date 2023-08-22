package com.atc.opportunity_management_system.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atc.opportunity_management_system.entity.ErrorMessage;

@ControllerAdvice
public class InvalidDatatypeException extends Exception {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }
}