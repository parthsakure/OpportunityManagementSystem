package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.service.OpportunityService;

import jakarta.validation.Valid;

@RestController
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @PutMapping("/api/opportunities/{id}")
    private Opportunity updateOpportunity(@PathVariable int id, @RequestBody @Valid Opportunity newOpportunity){

        return opportunityService.updateOpportunity(id, newOpportunity);
    }

    @PostMapping("/api/opportunities")
    private Object addOpportunity(@RequestBody @Valid Opportunity opportunity){

        return opportunityService.addOpportunity(opportunity);
    }
    
}
