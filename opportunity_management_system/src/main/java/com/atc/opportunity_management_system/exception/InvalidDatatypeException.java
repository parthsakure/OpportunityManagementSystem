
package com.atc.opportunity_management_system.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.atc.opportunity_management_system.entity.ErrorMessage;

@ControllerAdvice
public class InvalidDatatypeException extends Exception {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleinvalidDataException(HttpMessageNotReadableException e,WebRequest request) {
        String invalidDatatype = e.getLocalizedMessage().split("\"")[1];
        ErrorMessage error = new ErrorMessage("Enter Valid data instead of: "+invalidDatatype, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(error , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument (MethodArgumentNotValidException ex){
        Map<String,String> erroMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            erroMap.put(error.getField(), error.getDefaultMessage());
        });
        ErrorMessage error = new ErrorMessage(erroMap, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        ErrorMessage error = new ErrorMessage(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}
