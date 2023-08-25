package com.atc.opportunity_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.User;


public interface OpportunityRepository extends JpaRepository<Opportunity, Integer>{

    List<Opportunity> findByDealOwner(User dealOwner);
}
