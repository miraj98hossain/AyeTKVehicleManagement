package com.aye.backendservice.entity;

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
@Table(name = "STEP_TRANS", schema = "MAPPS")
public class StepTrans {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_trans_seq_gen"
    )
    @SequenceGenerator(
            name = "step_trans_seq_gen",
            sequenceName = "STEP_TRANS_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_TRANS_ID")
    private Long stepTransId;
    @Column(name = "STEP_TRANS_NO", unique = true, nullable = false)
    private String stepTransNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_SETUP_ID", referencedColumnName = "STEP_SETUP_ID")
    private StepSetup stepSetup;

    @Column(name = "VEHICLE_NUMBER", nullable = false)
    private String vehicleNumber;

    @Column(name = "DRIVER_PHONE_NO", nullable = false)
    private String driverPhoneNo;

    @Column(name = "DRIVER_NAME", nullable = false)
    private String driverName;

    @Column(name = "TRANSPORT_NAME", nullable = false)
    private String transportName;

    @OneToMany(mappedBy = "stepTrans", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OrderBy("createdAt asc ")
    private List<StepTransLines> stepTransLinesList = new ArrayList<>();

    @OneToOne(mappedBy = "stepTrans")
    private StepTransDetails stepTransDetails;


    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY", insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;
}
