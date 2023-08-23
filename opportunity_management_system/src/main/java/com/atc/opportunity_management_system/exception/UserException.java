package com.atc.opportunity_management_system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument (MethodArgumentNotValidException ex){
        Map<String,String> erroMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            erroMap.put(error.getField(), error.getDefaultMessage());
        });
        return erroMap;
    }
}
