package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="deliveryModel")
@Data
public class DeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryModelId;

    @Column(name="deliveryModelName", nullable = false)
    private String deliveryModel;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "deliveryModel")
    private List<Opportunity> opportunities = new ArrayList<>();


    
}
