package com.atc.opportunity_management_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Opportunity;
// import com.atc.opportunity_management_system.exception.InvalidDatatypeException;
// import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("opportunity")
public class OpportunityController {


    @Autowired
    private OpportunityService opportunityService;

    @GetMapping("/")
    private ResponseEntity<Object> getOpportunityByUser()
    {
        return opportunityService.getOpportunitiesByUser();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getOpportunityById(@PathVariable int id, @RequestParam boolean active)
    {
        return opportunityService.getOpportunityById(id);
    }

    @PutMapping("/{id}")
    private Opportunity updateOpportunity(@PathVariable int id, @RequestBody @Valid Opportunity newOpportunity) throws Exception
    {
        return opportunityService.updateOpportunity(id, newOpportunity);
    }

    @PostMapping("/")
    private Opportunity addOpportunity(@Valid @RequestBody Opportunity opportunity)
    {
        return opportunityService.addOpportunity(opportunity);
    }

    @DeleteMapping("/{id}")
    private void deleteOpportunity(@PathVariable int id)
    {
        opportunityService.deleteOpportunity(id);
    }

}
