package com.aye.commonlib.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 13:29
 * @project: AyeTKVehicleManagement
 */
@Data
public class StepTransTimeStampVResponse {
    private Long stepTransTlId;
    private String vehicleNumber;
    private Long stepSetupDetailsId;
    private Long stepTransLinesId;
    private String stepStatus;
    private LocalDateTime ignitionTime;
}
