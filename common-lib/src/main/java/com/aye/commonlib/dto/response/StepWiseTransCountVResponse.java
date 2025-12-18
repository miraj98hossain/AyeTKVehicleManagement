package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class StepWiseTransCountVResponse {
    private Long id;
    private Long stepSetupId;
    private Long stepSetupDetailsId;
    private Integer serialNo;
    private String prevStepName;
    private Integer prevStepCount;
    private String curStepName;
    private Integer curStepCount;
    private String forwStepName;
    private Integer forwStepCount;
}
