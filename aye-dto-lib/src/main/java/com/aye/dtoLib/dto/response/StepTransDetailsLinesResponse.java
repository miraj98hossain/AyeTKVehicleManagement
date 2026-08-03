package com.aye.dtoLib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 17:47
 * @project: AyeTKVehicleManagement
 */
@Data
public class StepTransDetailsLinesResponse {
    private Long stepTransDtlLnId;
    private String stepTransDtlLnNo;
    private Long stepTransDtlId;
    private String stepTransDtlNo;
    private Long invItemId;
    private String custName;
    private String orderedItem;
    private String stepStatus;
    private Integer stage;
    private BigDecimal orderedQuantity;
    private String uom;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
}
