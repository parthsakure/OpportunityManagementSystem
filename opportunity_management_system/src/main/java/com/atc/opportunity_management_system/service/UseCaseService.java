package com.atc.opportunity_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.UseCase;
import com.atc.opportunity_management_system.repository.UseCaseRepository;

@Service
public class UseCaseService {

    @Autowired
    UseCaseRepository useCaseRepository;
    
    //method to get all use cases
    public ResponseEntity<Object> getAllUseCases() {
        List<UseCase> usecases = useCaseRepository.findAll();
        if(usecases.isEmpty()){
            return new ResponseEntity<Object>(new ErrorMessage("No usecases found!", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usecases);
    }

    public UseCase getUseCase(int id) {
        return useCaseRepository.findById(id).orElse(null);
    }

    //method to add a usecase
    public ResponseEntity<Object> addUseCase(UseCase usecase)
    {
        //get if user is admin or not
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin)
        {
            //save the usecase
            usecase.setUseCaseId(0);
            return ResponseEntity.ok(useCaseRepository.save(usecase));
        }

        //if user is not admin
        return new ResponseEntity<Object>(new ErrorMessage("You are not an admin",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
    
    //method to delete a usecase
    public ResponseEntity<Object> deleteUseCase(int useCaseId)
    {
       //get if user is admin or not
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin)
        {
            //delete the usecase
            UseCase usecase = useCaseRepository.findById(useCaseId).get();
            useCaseRepository.delete(usecase);
            return ResponseEntity.ok().build();
        }

        //if user is not admin
        return new ResponseEntity<Object>(new ErrorMessage("You are not an admin",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST); 
    }
}
