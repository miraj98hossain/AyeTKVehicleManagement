package com.aye.commonlib.dto.request;

import com.aye.commonlib.dto.validationGroup.StepTransCreateValidation;
import com.aye.commonlib.dto.validationGroup.StepTransUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Transport name is required")
    private String transportName;

    @NotNull(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Step Setup id is required")
    private Long stepSetupId;

    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Driver name is required")
    private String driverName;

    @Size(max = 11, min = 11, message = "Phone number must be 11")
    @NotBlank(groups = {StepTransCreateValidation.class, StepTransUpdateValidation.class}, message = "Driver phone number is required")
    private String driverPhoneNo;
    private BigDecimal startNum;
    private BigDecimal endNum;
    private String challanNumber;

}
