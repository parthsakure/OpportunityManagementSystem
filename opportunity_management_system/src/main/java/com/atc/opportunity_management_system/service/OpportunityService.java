package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.DealStage;
import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.OpportunityRepository;
import com.atc.opportunity_management_system.repository.UserRepository;

@Service
public class OpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    UserRepository userrepository;

    public Opportunity changedealstage(int opportunityid, DealStage newdealStage)
    {
        //changing deal stage
        Opportunity opportunitytoupdate = opportunityRepository.findById(opportunityid).get();
        opportunitytoupdate.setDealStage(newdealStage);
        opportunityRepository.save(opportunitytoupdate);

        //identifying which user to give bbd bucks to
        User usertogetbbdbucks = userrepository.findById(opportunitytoupdate.getDealOwner().getUserId()).get();

        //current bbdbucks of user
        Integer bucks = usertogetbbdbucks.getBbdBucks();
        
        //adding the bucks according to deal stage
        switch(newdealStage.getDealStage()) {
            case "Qualified":
              usertogetbbdbucks.setBbdBucks(bucks + 500);
              break;
            case "Commited":
              usertogetbbdbucks.setBbdBucks(bucks + 5000);
              break;
            default:
        }
        userrepository.save(usertogetbbdbucks);

        //return
        return opportunityRepository.save(opportunitytoupdate);
    }
}
