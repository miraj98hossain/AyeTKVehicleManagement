package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    private Long invItemId;
    private String orderedItem;
    private BigDecimal orderedQuantity;
    private Long createdBy;
    private Date createdAt;
    private Long updatedBy;
    private Date updatedAt;
}
