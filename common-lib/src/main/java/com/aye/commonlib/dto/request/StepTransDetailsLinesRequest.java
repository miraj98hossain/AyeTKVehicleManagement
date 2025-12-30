package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 17:44
 * @project: AyeTKVehicleManagement
 */
@Data
public class StepTransDetailsLinesRequest {
    private Long stepTransDtlLnId;
    @NotNull(message = "Step Trans Dtl Id is required")
    private Long stepTransDtlId;
    private Long invItemId;
    private String orderedItem;
    private Double orderedQuantity;
}
