package com.atc.opportunity_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.Country;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public ResponseEntity<Object> getAllCountries()
    {
        List<Country> countries = new ArrayList<>();
        countries = countryRepository.findAll();

        if(countries.isEmpty())
        {
            return new ResponseEntity<Object>(new ErrorMessage("No Countries Present",HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);                             
        }
        return ResponseEntity.ok(countries);
    }
    
}
