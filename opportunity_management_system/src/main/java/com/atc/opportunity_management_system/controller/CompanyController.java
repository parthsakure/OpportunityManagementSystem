package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.service.CompanyService;


@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PutMapping("companies/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable Long companyId, @RequestBody Company updateCompany){
        if(companyService.updateCompany(companyId, updateCompany)){
            return ResponseEntity.ok("company updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("companies/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        if(companyService.deleteCompany(companyId)){
            return ResponseEntity.ok("company deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
