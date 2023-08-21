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

    public boolean updateCompany(Long companyId, Company updateCompany){
        Company company = companyRepository.findById(companyId).get();
        if(company!=null){
            if(company.isActive()){
                updateCompany.setCompanyId(companyId);
                companyRepository.save(updateCompany);
                return true;
            }
        }
        return false;
    }

@Transactional
    public boolean deleteCompany(Long companyId){
        Company company = companyRepository.findById(companyId).get();
        if(company!=null){
            if(company.isActive()){
                company.setActive(false);
                companyRepository.save(company);
                return true;
            }
        }
        return false;
    }

}
