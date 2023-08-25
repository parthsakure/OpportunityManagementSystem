package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.DeliveryModel;
import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.service.DeliveryModelService;

@RestController
@RequestMapping("/deliverymodel")
public class DeliveryModelController {

    @Autowired
    DeliveryModelService deliveryModelService;
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllDeliveryModels(){
        return deliveryModelService.getAllDeliveryModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDeliveryModel(@PathVariable Long id){
        DeliveryModel deliveryModel = deliveryModelService.getDeliveryModel(id);
        if(deliveryModel == null){
            return new ResponseEntity<>(new ErrorMessage("Delivery Model not found id: "+ id, HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(deliveryModel);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addDeliveryModel(@RequestBody DeliveryModel deliveryModel){
        return deliveryModelService.addDeliveryModel(deliveryModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDeliveryModel(@PathVariable Long id){
        return deliveryModelService.deleteDeliveryModel(id);
    }
}
