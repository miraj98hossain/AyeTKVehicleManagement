package com.mhdev.commonlib.dto.request;


import com.mhdev.commonlib.dto.validationGroup.StepSetupCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepSetupUpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepSetupRequest {
    @NotNull(groups = {StepSetupUpdateValidation.class},message = "Step Setup id is required")
    private Long stepSetupId;
    @NotNull(groups = {StepSetupCreateValidation.class,StepSetupUpdateValidation.class},message = "Org id is required")
    private Long orgId;
    @NotNull(groups = {StepSetupCreateValidation.class,StepSetupUpdateValidation.class},message = "Inv Org id is required")
    private Long invOrg;
    @NotNull(groups = {StepSetupCreateValidation.class,StepSetupUpdateValidation.class},message = "Active Status is required")
    private Integer isActive;
    @NotNull(groups = {StepSetupCreateValidation.class,StepSetupUpdateValidation.class},message = "Step setup details information is required")
    @Size(min = 1,message = "Minimum one Setup Details is required")
    private List<StepSetupDetailsRequest> stepSetupDetailsRequests;
}
