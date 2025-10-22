package com.mhdev.commonlib.dto.request;


import com.mhdev.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransLinesCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransLinesUpdateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransUpdateValidation;
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

public class StepTransLinesRequest {
    @NotNull(groups = {StepTransLinesUpdateValidation.class},message = "Step trans lines Id is required")
    private Long stepTransLinesId;
    @NotNull(groups = {StepTransLinesCreateValidation.class, StepTransLinesUpdateValidation.class},message = "Step trans id is required")
    private Long stepTransId;
    @NotNull(groups = {StepTransLinesCreateValidation.class, StepTransLinesUpdateValidation.class},message = "Step id is required")
    private Long stepId;
    @NotBlank(groups = {StepTransLinesCreateValidation.class, StepTransLinesUpdateValidation.class},message = "Step Status is required")
    private String stepStatus;
    private String remarks;
}
