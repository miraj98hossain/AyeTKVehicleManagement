package com.aye.dtoLib.dto.request;

import com.aye.dtoLib.dto.validationGroup.StepTransCreateValidation;
import com.aye.dtoLib.dto.validationGroup.StepTransUpdateValidation;
import com.aye.enums.TransportType;
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

    private Long stepTransId;
    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Vehicle City is required")
    private String vehicleCity;

    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Vehicle City Class is required")
    private String vehicleCityClass;

    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Vehicle number is required")
    private String vehicleNumber;

    @NotNull(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Transport name is required")
    private TransportType transportName;

    @NotNull(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Step Setup id is required")
    private Long stepSetupId;

    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Driver name is required")
    private String driverName;

    @Size(max = 11, min = 11, message = "Phone number must be 11")
    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Driver phone number is required")
    private String driverPhoneNo;
    private String challanNumber;

}
