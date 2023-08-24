package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public ResponseEntity<Object> getCompanies(@RequestParam(required = false) boolean active){
        return companyService.getCompanies(active);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Object> getCompanybyId(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return new ResponseEntity<Object>(new ErrorMessage("Company not found. Id: "+companyId,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Object> updateCompany(@PathVariable Long companyId, @RequestBody @Valid Company updateCompany) {
        return companyService.updateCompany(companyId, updateCompany);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long companyId) {
        return companyService.deleteCompany(companyId);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addCompany(@RequestBody @Valid Company company){
        return companyService.addCompany(company);
    }

}
