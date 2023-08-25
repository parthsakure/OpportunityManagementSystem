package com.atc.opportunity_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.DealStage;

public interface DealStageRepository extends JpaRepository<DealStage,Long> {

    Optional<DealStage> findByDealStage(String dealStage);
    
}
