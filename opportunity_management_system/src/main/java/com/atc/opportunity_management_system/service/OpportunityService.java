package com.atc.opportunity_management_system.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.DealStageRepository;
import com.atc.opportunity_management_system.repository.OpportunityRepository;

@Service
@RequestMapping("/api/opportunities")
public class OpportunityService {

    private OpportunityRepository opportunityRepository;
    
    @PutMapping("/{id}")
    public Opportunity updateOpportunity(@PathVariable int id, @RequestBody Opportunity newOpportunity){
        Opportunity opportunity = opportunityRepository.findById(id).get();
        if(opportunity == null){
            opportunity = newOpportunity;
        }
        User user = newOpportunity.getDealOwner();
        user.setBbdBucks(user.getBbdBucks() + newOpportunity.getDealStage().getRewardPrice());
        
        return opportunity;
    }
}
