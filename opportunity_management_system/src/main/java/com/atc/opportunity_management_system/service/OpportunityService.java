package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.DealStage;
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


    //method to create a transaction record
    private Transaction addTransactions(DealStage dealstage, Opportunity opportunity, User user)
    {
      Transaction transaction = new Transaction();

      //transactionId and timestamp is autogenerated
      transaction.setDealStage(dealstage);
      transaction.setOpportunity(opportunity);
      transaction.setUser(user);

      return transactionRepository.save(transaction);
    }

    //method to reward bbdbucks to user
    private User addBbdBucks(User user, int rewardPrice)
    {
      user.setBbdBucks(user.getBbdBucks() + rewardPrice);
      return userrepository.save(user);
    }



    //method to update an opportunity
    public Opportunity updateOpportunity(int opportunityid, Opportunity newOpportunity)
    {
        //getting the existing opportunity
        Opportunity opportunitytoupdate = opportunityRepository.findById(opportunityid)
            .orElseThrow(() -> new OpportunityNotFoundException(opportunityid));
        
        // if deal stage is changed
        if(opportunitytoupdate.getDealStage().getDealStageId() < newOpportunity.getDealStage().getDealStageId())
        {

          //identifying which user to give bbd bucks to
          User usertogetbbdbucks = userrepository.findById(newOpportunity.getDealOwner().getUserId()).get();

          //add to transactions table
          addTransactions(newOpportunity.getDealStage(), newOpportunity, usertogetbbdbucks);
          
          //adding the bucks according to deal stage
          addBbdBucks(usertogetbbdbucks, dealStageRepository.findById(newOpportunity.getDealStage().getDealStageId()).get().getRewardPrice());

        }

        else
        {
          return opportunitytoupdate;
        }

        //updating the opportunity
        return opportunityRepository.save(newOpportunity);

    }


    //method to add a new opportunity
    public Object addOpportunity(Opportunity opportunity)
    {
      //set the deal stage to prospect by deafult
      opportunity.setDealStage(dealStageRepository.findByDealStage("Prospect"));

      //save opportunity
      //opportunity.setOpportunityId(0);
      opportunityRepository.save(opportunity);
      
      //get user who submitted opportunity
      User usertogetbbdbucks = userrepository.findById(opportunity.getDealOwner().getUserId()).get();
      
      //add to transactions table
      addTransactions(dealStageRepository.findByDealStage("Prospect"), opportunity, usertogetbbdbucks);
      
      //addbbdbucks to the user
      opportunity.setDealOwner(addBbdBucks(usertogetbbdbucks, dealStageRepository.findByDealStage("Prospect").getRewardPrice()));
      
      return opportunity;
    }

}
