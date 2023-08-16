package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="industry")
@Data
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int industryId;

    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "industry")
    private List<Company> companies =  new ArrayList<>();

    // @ManyToOne 
    // private Country country;

}
