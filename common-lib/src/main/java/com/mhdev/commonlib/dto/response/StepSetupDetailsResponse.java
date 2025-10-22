package com.mhdev.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StepSetupDetailsResponse {
    private Long stepSetupDetailsId;
    private Long stepSetupId;
    private Long stepId;
    private Integer serialNo;
    private Integer isActive;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}
