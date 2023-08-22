package com.atc.opportunity_management_system.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;  

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpportunityNotFoundException extends RuntimeException{
     public OpportunityNotFoundException(int id){
        super("No opportunity found with id "+ id);
    }
}
