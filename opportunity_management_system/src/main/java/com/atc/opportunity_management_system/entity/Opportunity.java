package com.atc.opportunity_management_system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Data
@Table(name="opportunity")
public class Opportunity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="opportunityId")
    private int opportunityId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="primaryNeed")
    private String primaryNeed;

    @Column(name="expectedMonthlyRevenue")
    private BigDecimal expectedMonthlyRevenue;

    @Column(name="expectedLAunchDate")
    private Date expectedLAunchDate;

    @Column(name="closedLostReason")
    private String  closedLostReason;
    

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
        name="opportunity_usecase",
        joinColumns=@JoinColumn(name="opportunity"),
        inverseJoinColumns=@JoinColumn(name="useCase")
    )
    private List<UseCase> usecases = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="deliveryModel")
    private DeliveryModel deliveryModel;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="dealStage")
    private DealStage dealStage;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user")
    private User dealOwner;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "opportunity")
    private List<Transaction> transactions;
}