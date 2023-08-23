package com.atc.opportunity_management_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.Country;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.entity.Location;
import com.atc.opportunity_management_system.repository.CountryRepository;
import com.atc.opportunity_management_system.repository.IndustryRepository;
import com.atc.opportunity_management_system.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    CountryRepository countryRepository;

    public ResponseEntity<Object> updateLocation(Long Id, Location location)
    {
        if(location.getLocationId() != Id)
        {
            return new ResponseEntity<Object>(new ErrorMessage("Enter Same ID",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(locationRepository.save(location));
    }

    public ResponseEntity<Object> addLocation(Location location)
    {
        Optional<Country> country = countryRepository.findById( location.getCountry().getCountryId());
        if(!country.isPresent()){
            return new ResponseEntity<Object>(new ErrorMessage("Enter Valid Country ID",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }   
        
        location.setCountry(country.get());

        return ResponseEntity.ok(locationRepository.save(location));
    }
}
