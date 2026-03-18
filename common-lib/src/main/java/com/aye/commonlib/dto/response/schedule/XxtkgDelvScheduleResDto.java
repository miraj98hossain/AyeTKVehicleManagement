package com.aye.commonlib.dto.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XxtkgDelvScheduleResDto {

    private Long scheduleLineId;

    private Long scheduleId;

    private String scheduleNumber;

    private String scheduleStatus;

    private Date scheduleStartDate;

    private Date scheduleEndDate;

    private Long orgId;


    private String partyName;

    private Long custAccountId;

    private String itemCategories;

    private String itemFamily;

    private String itemClass;

    private Long headerId;

    private Long orderNumber;

    private Long lineId;

    private Long lineNumber;

    private String subinventory;

    private String orderedItem;

    private Long inventoryItemId;

    private Long organizationId;

    private String orderQuantityUom;

    private BigDecimal unitPrice;

    private BigDecimal orderedQty;

    private BigDecimal scheduleQty;

    private BigDecimal approveQty;

    private String lineStatus;

    private Date lineStartDate;

    private Date lineEndDate;

    private Long createdBy;

    private Date creationDate;

    private Long lastUpdatedBy;

    private Date lastUpdateDate;

    private Long lastUpdateLogin;
    private String deliveryInfo;

    private String remarks;

    private String vehicleInfo;

    private String moreInfo;

    private String modeOfTransport;

    private String modeOfTransname;

    private String workOrderNum;

    private Long dimOrdFlage;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal pcs;

    private Long mScheduleId;

    private Long mScheduleLine;

    private String paymentType;
}
