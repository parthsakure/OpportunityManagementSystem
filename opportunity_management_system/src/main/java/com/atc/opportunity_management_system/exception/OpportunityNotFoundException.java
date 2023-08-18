package com.atc.opportunity_management_system.exception;

public class OpportunityNotFoundException extends RuntimeException{
     public OpportunityNotFoundException(int id){
        super("No opportunity found with id "+ id);
    }
}
