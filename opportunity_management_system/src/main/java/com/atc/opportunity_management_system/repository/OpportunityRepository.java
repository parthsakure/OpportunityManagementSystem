package com.atc.opportunity_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.User;


@RepositoryRestResource(path = "opportunities")
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer>{

    List<Opportunity> findByDealOwner(User dealOwner);
}
