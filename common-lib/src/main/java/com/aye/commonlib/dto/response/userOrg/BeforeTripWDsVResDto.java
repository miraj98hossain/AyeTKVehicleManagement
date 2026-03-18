package com.aye.commonlib.dto.response.userOrg;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:31
 * @project: AyeTKVehicleManagement
 */
@Data
@NoArgsConstructor
public class BeforeTripWDsVResDto {

    private Long processLineId;

    private Long lineId;

    private Long headerId;

    private Long orgId;

    private Long orderNumber;

    private String orderNumberMode;

    private String name;

    private Date orderedDate;

    private Long lineNumber;

    private String orderedItem;

    private Long inventoryItemId;

    private BigDecimal orderedQuantity;

    private String flowStatusCode;

    private String type;

    private BigDecimal unitSellingPrice;

    private Date bookedDate;

    private String shipmentPriorityCode;

    private String additionalInfo;

    private String orderType;

    private String partyName;

    private String partyAddress;

    private Long custAccountId;

    private String subinventory;

    private String pricingQuantityUom;

    private String orderQuantityUom;

    private BigDecimal processScheduleQty;

    private Long invOrgId;

    private String invOrgName;

    private String attribute9;

    private String truck;

    private String legacy;

    private String driverName;

    private String tripDate;

    private Long subInvSl;

    private String meaning;

    private Long scheduleId;

    private String scheduleNumber;

    private Long scheduleLineId;

    private Date lineStartDate;

    private Date lineEndDate;

    private String lineStatus;

    private String vehicleInfo;

    private String moreInfo;

    private String modeOfTransport;

    private String modeOfTransname;

    private String workOrderNum;

    private Long dimOrdFlage;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal pcs;
}
