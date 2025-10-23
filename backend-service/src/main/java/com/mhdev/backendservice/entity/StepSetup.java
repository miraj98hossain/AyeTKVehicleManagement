package com.mhdev.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_SETUP",schema = "MAPPS")
public class StepSetup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STEP_SETUP_ID")
    private Long stepSetupId;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "INV_ORG")
    private Long invOrg;
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @OneToMany(mappedBy = "stepSetup",fetch = FetchType.LAZY)
    @OrderBy("serialNo ASC")
    private List<StepSetupDetails> stepSetupDetails=new ArrayList<>();


    @Column(name = "CREATED_BY",nullable = false,updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY",insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT",insertable = false)
    private Date updatedAt;
}
