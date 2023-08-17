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
import lombok.Data;

@Entity
@Table(name="industry")
@Data
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="industryId")
    private int industryId;

    @Column(name="industryName",unique=true,nullable = false)
    private String industry;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "industry")
    private List<Company> companies =  new ArrayList<>();



}
