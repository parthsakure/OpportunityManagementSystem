package com.atc.opportunity_management_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.entity.Location;
import com.atc.opportunity_management_system.repository.CompanyRepository;
import com.atc.opportunity_management_system.repository.IndustryRepository;
import com.atc.opportunity_management_system.repository.LocationRepository;

import jakarta.validation.Valid;


@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    LocationRepository locationRepository;

    public Company getCompanyById (Long companyId){
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company updateCompany(Long companyId, Company updateCompany){
        Company company = companyRepository.findById(companyId).get();
        if(company!=null){
            if(company.getActive()){
                updateCompany.setCompanyId(companyId);
                return companyRepository.save(updateCompany);

            }
        }
        return null;
    }

    @Transactional
    public Company deleteCompany(Long companyId){
        Company company = companyRepository.findById(companyId).get();
        if(company!=null){
            if(company.getActive()){
                company.setActive(false);
                return companyRepository.save(company);
            }
        }
        return null;
    }

    @Transactional
    public ResponseEntity<Object> addCompany(@Valid Company company) {
        Optional<Industry> industry = industryRepository.findById((long) company.getIndustry().getIndustryId());
        if(!industry.isPresent()){
            return new ResponseEntity<Object>(new ErrorMessage("Enter Valid Industry ID",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
        Optional<Location> location = locationRepository.findById((long) company.getLocation().getLocationId());
        if(!location.isPresent()){
            return new ResponseEntity<Object>(new ErrorMessage("Enter Valid Location ID",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
        company.setIndustry(industry.get());
        company.setLocation(location.get());
        return ResponseEntity.ok(companyRepository.save(company));
        
    }


}
