package com.atc.opportunity_management_system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
@Data
public class Opportunity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opportunityId ;

    private String title;

    private String description;

    private String primaryNeed;

    private BigDecimal expectedMonthlyRevenue;

    private Date expectedLAunchDate;

    private String  closedLostReason;
    
    private int deliveryModelId_fk;

    private int dealStageId_fk;
    
    private int dealOwnerId_fk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunity")
    private List<opportunity_usecase_map> opportunity_usecase_maps = new ArrayList<>();
}