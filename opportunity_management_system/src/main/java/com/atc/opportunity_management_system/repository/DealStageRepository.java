package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.DealStage;

@RepositoryRestResource(path="dealstages")
public interface DealStageRepository extends JpaRepository<DealStage,Integer> {

    DealStage findByDealStage(String dealStage);
    
}
