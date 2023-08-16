package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class opportunity_usecase_map {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private int opportunityId_fk;

    private int usecaseId_fk;

    @ManyToOne
    private Opportunity opportunity;

    @ManyToOne
    private UseCase usecase;

}
