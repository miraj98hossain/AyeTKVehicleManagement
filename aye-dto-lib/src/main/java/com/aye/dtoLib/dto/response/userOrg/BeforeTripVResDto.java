package com.aye.dtoLib.dto.response.userOrg;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 11:17
 * @project: AyeTKVehicleManagement
 */

@Data
@NoArgsConstructor
public class BeforeTripVResDto {
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

    private Long custAccountId;

    private String subinventory;

    private String pricingQuantityUom;

    private String orderQuantityUom;

    private Long invOrgId;

    private String invOrgName;

    private String attribute9;

    private String truck;

    private String legacy;

    private String driverName;

    private String tripDate;

    private Long subInvSl;

    private String meaning;

    private Character scdFlag;
}
