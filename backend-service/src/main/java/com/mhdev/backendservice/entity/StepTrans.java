package com.mhdev.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_TRANS",schema = "MAPPS")
public class StepTrans {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STEP_TRANS_ID")
    private Long stepTransId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_SETUP_ID",referencedColumnName = "STEP_SETUP_ID")
    private StepSetup stepSetup;

    @Column(name = "VEHICLE_NUMBER",nullable = false)
    private String vehicleNumber;

    @Column(name = "DRIVER_PHONE_NO",nullable = false)
    private String driverPhoneNo;

    @Column(name = "PARTY_NAME",nullable = false)
    private String partyName;

    @Column(name = "ITEM",nullable = false)
    private String item;

    @Column(name = "QUANTITY",nullable = false)
    private Double quantity;

    @Column(name = "TRANSPORT_NAME",nullable = false)
    private String transportName;

    @OneToMany(mappedBy = "stepTrans",fetch = FetchType.LAZY)
    @OrderBy("createdAt desc")
    private List<StepTransLines> stepTransLinesList =new ArrayList<>();

    @Column(name = "CREATED_BY",nullable = false,updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY",insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT",insertable = false)
    private Date updatedAt;
}
