package com.aye.dtoLib.dto.response;

import lombok.Data;

/**
 * @author: Miraj
 * @date: 06/06/2026
 * @time: 1:59 pm
 */
@Data
public class StepWiseVehicleSummaryVDto {
    private Long id;
    private String description;
    private Long orgId;
    private Long invOrg;
    private Long stepSetupDetailsId;
    private String serialNo;
    private Long stepId;
    private String stepName;
    private Long vehicleCount;
}
