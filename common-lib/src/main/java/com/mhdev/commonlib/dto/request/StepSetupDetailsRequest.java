package com.mhdev.commonlib.dto.request;

import com.mhdev.commonlib.dto.validationGroup.StepSetupDetailsCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepSetupDetailsUpdateValidation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepSetupDetailsRequest {
    @NotNull(groups = {StepSetupDetailsUpdateValidation.class},message = "Step Setup Details id is required")
    private Long stepSetupDetailsId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class,StepSetupDetailsUpdateValidation.class},message = "Factory id is required")
    private Long stepSetupId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class,StepSetupDetailsUpdateValidation.class},message = "Step id is required")
    private Long stepId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class,StepSetupDetailsUpdateValidation.class},message = "Step Details is active is required")
    private Integer isActive;
}
