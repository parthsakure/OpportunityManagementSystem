package com.atc.opportunity_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.DealStage;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.repository.DealStageRepository;

@Service
public class DealStageService {

    @Autowired
    DealStageRepository dealStageRepository;

    //method to get all deal stages
    public ResponseEntity<Object> getAllDealStages()
    {
        //check if user is admin
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("ROLE_ADMIN"));
        
        if(isAdmin)
        {
            //get all deal stages
            List<DealStage> dealStages = dealStageRepository.findAll();

            //if there are no deal stages
            if(dealStages.isEmpty())
            {
                return new ResponseEntity<Object>(new ErrorMessage("No Deal Stages Present",HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);                             
            }

            //return all deal stages
            return ResponseEntity.ok(dealStages);
        }
        
        //if user is not admin
        return new ResponseEntity<Object>(new ErrorMessage("You are not an admin",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
