package com.atc.opportunity_management_system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//mport org.hibernate.annotations.ManyToAny;

import lombok.Data;
// // import java.util.ArrayList;
// // import java.util.List;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;


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

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "opportunity")
    private List<UseCase> usecases = new ArrayList<>();

    @ManyToOne
    private DeliveryModel deliverymodel;

    @ManyToOne
    private DealStage dealstage;

    @ManyToOne
    private User user;
}