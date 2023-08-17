package com.atc.opportunity_management_system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import io.micrometer.common.lang.Nullable;
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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Data
@Table(name="opportunity")
public class Opportunity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="opportunityId")
    private int opportunityId;

    @Column(name="title")
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @Column(name="description")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Column(name="primaryNeed")
    @NotEmpty(message = "PrimaryNeed cannot be empty")
    private String primaryNeed;

    @Column(name="expectedMonthlyRevenue")
    @NotEmpty(message = "ExpectedMonthlyRevenue cannot be empty")
    private BigDecimal expectedMonthlyRevenue;

    @Column(name="expectedLaunchDate")
    @NotEmpty(message = "ExpectedLaunchDate cannot be empty")
    @Future(message = "Expected launch date must be in the future")
    private Date expectedLaunchDate;

    @Column(name="closedLostReason") 
    @Nullable
    private String  closedLostReason;
    

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
        name="opportunity_usecase",
        joinColumns=@JoinColumn(name="opportunity"),
        inverseJoinColumns=@JoinColumn(name="useCase")
    )
    @NotEmpty(message = "UseCases cannot be empty")
    private List<UseCase> usecases = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="deliveryModel")
    @NotEmpty(message = "DeliveryModel cannot be empty")
    private DeliveryModel deliveryModel;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="dealStage")
    @NotEmpty(message = "DealStage cannot be empty")
    private DealStage dealStage;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user")
    @NotEmpty(message = "DealOwner cannot be empty")
    private User dealOwner;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "opportunity")
    @NotEmpty(message = "Transactions cannot be empty")
    private List<Transaction> transactions;
}