package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Location;
import com.atc.opportunity_management_system.service.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;


    @PutMapping("/{Id}")
    private ResponseEntity<Object> updateLocation(@PathVariable Long Id, @RequestBody @Valid Location location){

        return locationService.updateLocation(Id, location);
    }

    @PostMapping("/")
    private ResponseEntity<Object> addLocation(@Valid @RequestBody Location location)
    {
        return locationService.addLocation(location);
    }
}
