package com.mhdev.commonlib.dto.request;

import jakarta.validation.constraints.DecimalMin;
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
public class StepTransDetailsRequest {
    private Long stepTransDtlId;
    @NotNull(message = "Step Trans id is required")
    private Long stepTransId;
    @NotBlank(message = "Customer Name is required")
    private String custName;
    @NotBlank(message = "Item Name is required")
    private String item;
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity can not be zero")
    @NotNull(message = "Quantity is required")
    private Double quantity;
    private Long tripNo;
    private Long scheduleNo;
}
