package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.DeliveryModel;

public interface DeliveryModelRepository extends JpaRepository<DeliveryModel,Long> {
    
}
