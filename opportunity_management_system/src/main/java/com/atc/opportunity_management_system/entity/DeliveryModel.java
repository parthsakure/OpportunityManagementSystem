package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "deliveryModel")
@Data
public class DeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryModelId;

    @Column(name = "deliveryModelName", nullable = false)
    // @NotEmpty
    private String deliveryModel;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryModel")
    private List<Opportunity> opportunities = new ArrayList<>();

}
