package com.atc.opportunity_management_system.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Data
@Table(name = "opportunity")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opportunityId")
    //@NotNull(message = "can't keep this field empty") 
    private int opportunityId;

    @Column(name = "title", nullable = false)
    //@NotNull(message = "can't keep this field empty") 
    private String title;

    @Column(name = "description", nullable = false)
    //@NotNull(message = "can't keep this field empty")     
    private String description;

    @Column(name = "primaryNeed", nullable = false)
    //@NotNull(message = "can't keep this field empty") 
    private String primaryNeed;

    @Column(name = "expectedMonthlyRevenue", nullable = false)
    @PositiveOrZero(message = "The ExpectedMonthly revenue cannot be negative")
    private BigDecimal expectedMonthlyRevenue;

    @Column(name = "expectedLaunchDate", nullable = false)
    @Future(message = "Expected launch date must be in the future")
    private OffsetDateTime expectedLaunchDate;

    @Column(name = "closedLostReason")
    @Nullable
    private String closedLostReason;

    @Column(name = "active")
    @Value("true")
    private boolean active;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH })
    // @JsonIgnore
    @JoinTable(name = "opportunity_usecase", joinColumns = @JoinColumn(name = "opportunity"), inverseJoinColumns = @JoinColumn(name = "useCase"))
    private List<UseCase> usecases = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "deliveryModel")
    private DeliveryModel deliveryModel;

    @ManyToOne
    @JoinColumn(name = "dealStage")
    private DealStage dealStage;

    @ManyToOne
    @JoinColumn(name = "userDetails")
    private User dealOwner;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunity")
    private List<Transaction> transactions;
}