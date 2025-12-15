package com.aye.backendservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "STEP_WISE_TRANS_COUNT_V", schema = "MAPPS")
@Data
public class StepWiseTransCountV {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "STEP_SETUP_ID")
    private Long stepSetupId;
    @Column(name = "STEP_SETUP_DETAILS_ID")
    private Long stepSetupDetailsId;
    @Column(name = "SERIAL_NO")
    private Integer serialNo;
    @Column(name = "PREV_STEP_NAME")
    private String prevStepName;
    @Column(name = "PREV_STEP_COUNT")
    private Integer prevStepCount;
    @Column(name = "CUR_STEP_NAME")
    private String curStepName;
    @Column(name = "FORW_STEP_NAME")
    private String forwStepName;
    @Column(name = "FORW_STEP_COUNT")
    private Integer forwStepCount;
}
