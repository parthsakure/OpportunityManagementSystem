package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.repository.CompanyRepository;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyById (Long companyId){
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company updateCompany(Long companyId, Company updateCompany){
        Company company = companyRepository.findById(companyId).get();
        if(company!=null){
            if(company.isActive()){
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
            if(company.isActive()){
                company.setActive(false);
                return companyRepository.save(company);
            }
        }
        return null;
    }


}
