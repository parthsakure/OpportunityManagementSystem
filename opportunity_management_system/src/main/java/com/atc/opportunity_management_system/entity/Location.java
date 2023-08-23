package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "location")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(name = "postalCode", nullable = false, length = 10)
    private int postalCode;

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "location")
    private List<Company> companies = new ArrayList<>();
}
