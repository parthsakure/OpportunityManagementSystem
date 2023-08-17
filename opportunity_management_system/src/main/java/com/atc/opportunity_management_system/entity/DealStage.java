package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="dealStage")
@Data
public class DealStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dealStage", nullable = false)
    private String dealStage;

    @Column(name="rewardPrice", nullable = false)
    private int rewardPrice;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "dealStage")
    private List<Opportunity> opportunities = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "dealStage")
    private List<Transaction> transactions = new ArrayList<>();

    public DealStage(String dealStage, int rewardPrice) {
        this.dealStage = dealStage;
        this.rewardPrice = rewardPrice;
    }
}
