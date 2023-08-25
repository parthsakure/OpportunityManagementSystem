package com.atc.opportunity_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.UseCase;
import com.atc.opportunity_management_system.repository.UseCaseRepository;

@Service
public class UseCaseSevice {

    @Autowired
    UseCaseRepository useCaseRepository;

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
    
}
