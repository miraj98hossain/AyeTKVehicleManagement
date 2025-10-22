package com.mhdev.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP",schema = "MAPPS")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="STEP_ID")
    private Long stepId;
    @Column(name = "STEP_NAME",nullable = false)
    private String stepName;
    @Column(name = "IS_ACTIVE",nullable = false,length = 1)
    private Integer isActive;

    @Column(name = "CREATED_BY",nullable = false,updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY",insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT",insertable = false)
    private Date updatedAt;
}
