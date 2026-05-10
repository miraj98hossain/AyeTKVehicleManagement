package com.aye.dtoLib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepTransLinesResponse {
    private Long stepTransLinesId;
    private String stepTransLinesNo;
    private Long stepTransId;
    private Long stepSetupDetailsId;
    private String stepTransNo;
    private String vehicleNumber;
    private String transportName;
    private String driverPhoneNo;
    private String driverName;
    private Long stepId;
    private String stepName;
    private String stepStatus;
    private String holdBy;
    private boolean isHoldAble;
    private String currentlyLoadingItem;
    private String remarks;
    private Integer stage;
    private List<StepTransDetailsResponse> stepTransDetails;
    private StepTransScaleDetailsDto stepTransScaleDetails;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}
