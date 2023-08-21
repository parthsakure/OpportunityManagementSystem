package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.Transaction;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.exception.OpportunityNotFoundException;
import com.atc.opportunity_management_system.repository.DealStageRepository;
import com.atc.opportunity_management_system.repository.OpportunityRepository;
import com.atc.opportunity_management_system.repository.TransactionRepository;
import com.atc.opportunity_management_system.repository.UserRepository;

@Service
public class OpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    UserRepository userrepository;

    @Autowired
    DealStageRepository dealStageRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Opportunity updateOpportunity(int opportunityid, Opportunity newOpportunity)
    {
        //getting the existing opportunity
        Opportunity opportunitytoupdate = opportunityRepository.findById(opportunityid)
            .orElseThrow(() -> new OpportunityNotFoundException(opportunityid));
        

        //updating the opportunity
        if(opportunitytoupdate!= newOpportunity)
        {
          opportunitytoupdate.setTitle(newOpportunity.getTitle());
          opportunitytoupdate.setDescription(newOpportunity.getDescription());
          opportunitytoupdate.setPrimaryNeed(newOpportunity.getPrimaryNeed());
          opportunitytoupdate.setExpectedMonthlyRevenue(newOpportunity.getExpectedMonthlyRevenue());
          opportunitytoupdate.setExpectedLaunchDate(newOpportunity.getExpectedLaunchDate());
          opportunitytoupdate.setClosedLostReason(newOpportunity.getClosedLostReason());
          opportunitytoupdate.setDeliveryModel(newOpportunity.getDeliveryModel());
          opportunitytoupdate.setDealStage(newOpportunity.getDealStage());
          opportunitytoupdate.setDealOwner(newOpportunity.getDealOwner());

          opportunityRepository.save(opportunitytoupdate);
        }

        else{
          return opportunitytoupdate;
        }


        // if deal stage is changed
        if(opportunitytoupdate.getDealStage() != newOpportunity.getDealStage())
        {

          //identifying which user to give bbd bucks to
          User usertogetbbdbucks = userrepository.findById(opportunitytoupdate.getDealOwner().getUserId()).get();

          //current bbdbucks of user
          Integer bucks = usertogetbbdbucks.getBbdBucks();

          //add to transactions table
          Transaction transaction = new Transaction();
          transaction.setDealStage(newOpportunity.getDealStage());
          transaction.setOpportunity(opportunitytoupdate);
          transaction.setUser(usertogetbbdbucks);

          transactionRepository.save(transaction);
          

          //adding the bucks according to deal stage
          switch(newOpportunity.getDealStage().getDealStage()) {
              case "Qualified":
                usertogetbbdbucks.setBbdBucks(bucks + 500);
                break;
              case "Committed":
                usertogetbbdbucks.setBbdBucks(bucks + 5000);
                break;
              default:
                break;
          }

          //saving the user with updated bucks
          userrepository.save(usertogetbbdbucks);
        }

        //return
        return opportunitytoupdate;
    }
}
