package com.mhdev.commonlib.dto.request;

import com.mhdev.commonlib.dto.validationGroup.StepCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepUpdateValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepRequest {
    @NotNull(groups = {StepUpdateValidation.class}, message = "Step id is required to update")
    private Long stepId;
    @NotBlank(groups = {StepCreateValidation.class, StepUpdateValidation.class}, message = "Step Name is required")
    private String stepName;
    @NotNull(groups = {StepCreateValidation.class, StepUpdateValidation.class}, message = "Step is active is required")
    @Min(value = 0, message = "Is Active must be 0 or 1")
    @Max(value = 1, message = "Is Active must be 0 or 1")
    private Integer isActive;
}
