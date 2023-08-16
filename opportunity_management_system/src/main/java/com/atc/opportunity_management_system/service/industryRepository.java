package com.atc.opportunity_management_system.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.industry;


@RepositoryRestResource(path="industry")
public interface industryRepository extends JpaRepository <industry ,Long>{

}
