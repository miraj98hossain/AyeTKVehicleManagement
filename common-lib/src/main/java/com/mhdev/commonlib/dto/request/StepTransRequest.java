package com.mhdev.commonlib.dto.request;

import com.mhdev.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.StepTransUpdateValidation;
import jakarta.validation.constraints.DecimalMin;
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

public class StepTransRequest {
    @NotNull(groups = {StepTransUpdateValidation.class},message = "Step trans id is required to update")
    private Long stepTransId;

    @NotBlank(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Vehicle number is required")
    private String vehicleNumber;

    @NotNull(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Step Setup id is required")
    private Long stepSetupId;

    @Size(max = 11,min = 11,message = "Phone number must be 11")
    @NotBlank(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Driver phone number is required")
    private String driverPhoneNo;

    @NotBlank(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Party name is required")
    private String partyName;

    @NotBlank(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Item name is required")
    private String item;

    @DecimalMin(value = "0.0",inclusive = false, groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Quantity can not be zero")
    @NotNull(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Quantity is required")
    private Double quantity;


    @NotBlank(groups = {StepTransCreateValidation.class,StepTransUpdateValidation.class},message = "Transport name is required")
    private String transportName;
}
