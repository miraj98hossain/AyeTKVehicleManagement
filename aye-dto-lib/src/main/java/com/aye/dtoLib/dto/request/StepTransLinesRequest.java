package com.aye.dtoLib.dto.request;


import com.aye.dtoLib.dto.validationGroup.StepTransLinesCreateValidation;
import com.aye.dtoLib.dto.validationGroup.StepTransLinesUpdateValidation;
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
    @NotNull(groups = {StepTransLinesUpdateValidation.class}, message = "Step trans lines Id is required")
    private Long stepTransLinesId;
    @NotBlank(groups = {StepTransLinesCreateValidation.class, StepTransLinesUpdateValidation.class}, message = "Step Status is required")
    private String stepStatus;
    private String remarks;
}
