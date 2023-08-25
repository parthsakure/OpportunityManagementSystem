package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
    
    @Autowired
    CountryService countryService;

    @GetMapping("/")
    private ResponseEntity<Object> getAllCountries()
    {
        return countryService.getAllCountries();
    }
}
