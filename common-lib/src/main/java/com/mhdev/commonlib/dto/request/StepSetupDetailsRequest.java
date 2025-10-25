package com.mhdev.commonlib.dto.request;

import com.mhdev.commonlib.dto.validationGroup.StepSetupDetailsCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepSetupDetailsUpdateValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @NotNull(groups = {StepSetupDetailsUpdateValidation.class}, message = "Step Setup Details id is required")
    private Long stepSetupDetailsId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class, StepSetupDetailsUpdateValidation.class}, message = "Factory id is required")
    private Long stepSetupId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class, StepSetupDetailsUpdateValidation.class}, message = "Step id is required")
    private Long stepId;
    @NotNull(groups = {StepSetupDetailsCreateValidation.class, StepSetupDetailsUpdateValidation.class}, message = "Step Details is active is required")
    @Min(value = 0, message = "Is Active must be 0 or 1")
    @Max(value = 1, message = "Is Active must be 0 or 1")
    private Integer isActive;
}
