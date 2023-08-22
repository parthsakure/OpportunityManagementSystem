package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.exception.InvalidDatatypeException;
// import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {


    @Autowired
    private OpportunityService opportunityService;

    @PutMapping("/{id}")
    private Opportunity updateOpportunity(@PathVariable int id, @RequestBody @Valid Opportunity newOpportunity){

        return opportunityService.updateOpportunity(id, newOpportunity);
    }

    @PostMapping("/")
    private Opportunity addOpportunity(@Valid @RequestBody Opportunity opportunity){

        return opportunityService.addOpportunity(opportunity);
    }

    @GetMapping("/{id}")
    private Opportunity getOpportunityById(@PathVariable int id)
    {
        return opportunityService.getOpportunityById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteOpportunity(@PathVariable int id,@RequestBody Opportunity delOpportunity)
    {
        opportunityService.deleteOpportunity(id, delOpportunity);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception e){
        ErrorMessage error = new ErrorMessage(e.getMessage(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
        System.out.println("**********************************************");
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);

    }
}
