package com.aye.dtoLib.dto.response;

import lombok.Data;

@Data
public class StepWiseTransCountVResponse {
    private Long id;
    private Long stepSetupId;
    private Long stepSetupDetailsId;
    private Long pStepSetupDetailsId;
    private Long fStepSetupDetailsId;
    private Integer serialNo;
    private String prevStepName;
    private Integer prevStepCount;
    private String curStepName;
    private Integer curStepCount;
    private String forwStepName;
    private Integer forwStepCount;
}
