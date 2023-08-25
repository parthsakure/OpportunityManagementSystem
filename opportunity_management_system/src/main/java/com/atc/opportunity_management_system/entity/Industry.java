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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "industry")
@Data
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industryId")
    private int industryId;

    @Column(name = "industryName", unique = true, nullable = false)
    @NotNull(message = "can't keep this field empty") 
    private String industry;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "industry")
    private List<Company> companies = new ArrayList<>();

}
