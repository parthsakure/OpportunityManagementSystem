package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.service.IndustryService;

@RestController
@RequestMapping("/industry")
public class IndustryController {
    
    @Autowired
    IndustryService industryService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllIndustries(){
        return industryService.getAllIndustries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIndustry(@PathVariable Long id){
        Industry industry = industryService.getIndustry(id);
        if(industry==null){
            return new ResponseEntity<Object>(new ErrorMessage("Industry Not Found! id:"+id, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(industry);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addIndustry(@RequestBody Industry industry){
        return industryService.addIndustry(industry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIndustry(@PathVariable Long id){
        return industryService.deleteIndustry(id);
    }
}
