package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.DeliveryModel;
import com.atc.opportunity_management_system.repository.DeliveryModelRepository;

@Service
public class DeliveryModelService {

    @Autowired
    DeliveryModelRepository deliveryModelRepository;

    public ResponseEntity<Object> getAllDeliveryModels() {
        return ResponseEntity.ok().body(deliveryModelRepository.findAll());
    }

    public DeliveryModel getDeliveryModel(int id) {
        return deliveryModelRepository.findById(id).orElse(null);
    }

    

}
