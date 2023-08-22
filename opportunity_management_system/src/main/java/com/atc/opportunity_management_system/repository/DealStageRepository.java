package com.atc.opportunity_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.DealStage;

@RepositoryRestResource(path="dealstages")
public interface DealStageRepository extends JpaRepository<DealStage,Long> {

    Optional<DealStage> findByDealStage(String dealStage);
    
}
