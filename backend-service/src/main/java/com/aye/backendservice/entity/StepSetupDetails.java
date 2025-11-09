package com.aye.backendservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_SETUP_DETAILS", schema = "MAPPS")
public class StepSetupDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_setup_details_seq_gen"
    )
    @SequenceGenerator(
            name = "step_setup_details_seq_gen",
            sequenceName = "STEP_SETUP_DETAILS_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_SETUP_DETAILS_ID")
    private Long stepSetupDetailsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_SETUP_ID", nullable = false, referencedColumnName = "STEP_SETUP_ID")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"stepSetupDetails"})
    private StepSetup stepSetup;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_ID", nullable = false, referencedColumnName = "STEP_ID")
    private Step step;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Integer isActive;

    @Column(name = "SERIAL_NO", nullable = false)
    private Integer serialNo = 0;

    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private Long createdBy;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "UPDATED_BY", insertable = false)
    private Long updatedBy;

    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;
}
