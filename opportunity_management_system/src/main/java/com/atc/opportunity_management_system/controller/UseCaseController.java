package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.UseCase;
import com.atc.opportunity_management_system.service.UseCaseSevice;

@RestController
@RequestMapping("/usecase")
public class UseCaseController {

    @Autowired
    UseCaseSevice useCaseSevice;
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllUseCases(){
        return useCaseSevice.getAllUseCases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUseCase(@PathVariable int id){
        UseCase useCase = useCaseSevice.getUseCase(id);
        if(useCase == null){
            return new ResponseEntity<Object>(new ErrorMessage("Usecase Not Found id: "+id, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(useCase);
    }
}
