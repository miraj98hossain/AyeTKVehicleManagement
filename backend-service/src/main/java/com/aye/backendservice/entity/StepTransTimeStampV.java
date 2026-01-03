package com.aye.backendservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:21
 * @project: AyeTKVehicleManagement
 */
@Entity
@Getter
@Setter
@Immutable
@Table(name = "STEP_TRANS_TIME_STAMP_V", schema = "MAPPS")
public class StepTransTimeStampV {

    @Id
    @Column(name = "STEP_TRANS_TL_ID")
    private Long stepTransTlId;

    @Column(name = "VEHICLE_NUMBER")
    private String vehicleNumber;

    @Column(name = "STEP_SETUP_DETAILS_ID")
    private Long stepSetupDetailsId;

    @Column(name = "STEP_TRANS_LINES_ID")
    private Long stepTransLinesId;

    @Column(name = "STEP_STATUS")
    private String stepStatus;

    @Column(name = "IGNITION_TIME")
    private LocalDateTime ignitionTime;

}
