package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "dealStage")
@Data
public class DealStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealStageId;

    @Column(name = "dealStageName", nullable = false)
    @NotEmpty
    private String dealStage;

    @Column(name = "rewardPrice", nullable = false)
    @NotEmpty
    private int rewardPrice;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealStage")
    private List<Opportunity> opportunities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealStage")
    private List<Transaction> transactions = new ArrayList<>();

}
