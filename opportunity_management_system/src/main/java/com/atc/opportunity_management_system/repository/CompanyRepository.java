package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.Company;



@RepositoryRestResource(path="company")
public interface CompanyRepository extends JpaRepository <Company ,Long>{
}
