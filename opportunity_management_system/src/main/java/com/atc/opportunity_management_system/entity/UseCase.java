package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="useCase")
@Data
public class UseCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useCaseId;

    @Column(name="useCaseName", nullable = false)
    private String useCase;

    @ManyToMany( cascade = { CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
        name="opportunity_usecase",
        joinColumns=@JoinColumn(name="useCase"),
        inverseJoinColumns=@JoinColumn(name="opportunity")
    )
    private List<Opportunity> opportunities = new ArrayList<>();

}
