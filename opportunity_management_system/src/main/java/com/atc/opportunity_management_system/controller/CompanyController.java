package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanybyId(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestBody @Valid Company updateCompany) {
        Company company = companyService.updateCompany(companyId, updateCompany);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long companyId) {
        Company company = companyService.deleteCompany(companyId);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Object> addCompany(@RequestBody @Valid Company company){
        return companyService.addCompany(company);
    }

}
