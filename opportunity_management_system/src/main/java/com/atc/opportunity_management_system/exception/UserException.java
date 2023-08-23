package com.atc.opportunity_management_system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserException {
    public Map<String,String> handleInvalidArgument (MethodArgumentNotValidException ex){
        Map<String,String> erroMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            erroMap.put(error.getField(), error.getDefaultMessage());
        });
        return erroMap;
    }
}
