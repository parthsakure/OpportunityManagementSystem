package com.atc.opportunity_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.repository.IndustryRepository;

import jakarta.transaction.Transactional;

@Service
public class IndustryService {

    @Autowired
    IndustryRepository industryRepository;

    public ResponseEntity<Object> getAllIndustries() {
        List<Industry> industries = industryRepository.findAll();
        if(industries.isEmpty()){
            return new ResponseEntity<Object>(new ErrorMessage("No Industries Found!", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(industries);
    }

    public Industry getIndustry(Long id) {
        return industryRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<Object> addIndustry(Industry industry) {
        industry.setIndustryId(0);
        return ResponseEntity.ok(industryRepository.save(industry));
    }

    public ResponseEntity<Object> deleteIndustry(Long id) {
        industryRepository.deleteById(id);
        return ResponseEntity.ok("Industry deleted Successfully!");
    }

    
    

}
