package com.aye.commonlib.dto.response;


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
public class StepSetupResponse {

    private Long stepSetupId;
    private Long orgId;
    private Long invOrg;
    private Integer isActive;
    private List<StepSetupDetailsResponse> stepSetupDetails;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;

}
