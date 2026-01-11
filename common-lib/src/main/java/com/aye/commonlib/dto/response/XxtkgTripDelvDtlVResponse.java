package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author: Miraj
 * @date: 08/01/2026
 * @time: 11:47
 */
@Data
public class XxtkgTripDelvDtlVResponse {

    private Long transactionId;
    private Long orgId;
    private Long shipFromOrgId;
    private Long orderNumber;
    private Long headerId;
    private Long lineId;
    private String orderedItem;
    private Long inventoryItemId;
    private String orderQuantityUom;
    private BigDecimal orderedQuantity;
    private BigDecimal shippedQuantity;
    private LocalDate actualShipmentDate;
    private String subinventory;
    private String custPoNumber;
    private String challanNumber;
    private String tripCreateBy;
    private String vehicleNo;
    private String driverName;
    private String additionalInfo;
    private String flowStatusCode;
    private String transportDetails;
    private Long transactionTypeId;
    private String transactionTypeName;
}
