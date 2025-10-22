package com.mhdev.commonlib.dto.request;

import com.mhdev.commonlib.dto.validationGroup.StepCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepRequest {
    @NotNull(groups = {StepUpdateValidation.class},message = "Step id is required to update")
    private Long stepId;
    @NotBlank(groups = {StepCreateValidation.class, StepUpdateValidation.class},message = "Step Name is required")
    private String stepName;
    @NotNull(groups = {StepCreateValidation.class, StepUpdateValidation.class},message = "Step is active is required")
    @Size(min = 1,max = 1,message = "Is Active should be 1 character 0 or 1")
    private Integer isActive;
}
