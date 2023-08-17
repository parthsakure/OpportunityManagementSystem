package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.DealStage;
import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.service.OpportunityService;

@RestController
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @PutMapping("/changedealstage")
    private Opportunity changedealstage(int opportunityid, DealStage newdealStage){

        return opportunityService.changedealstage(opportunityid, newdealStage);
    }
    
}
