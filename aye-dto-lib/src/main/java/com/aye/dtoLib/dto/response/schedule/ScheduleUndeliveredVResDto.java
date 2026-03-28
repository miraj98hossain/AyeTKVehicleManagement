package com.aye.dtoLib.dto.response.schedule;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUndeliveredVResDto {


    private Long id;


    private Long orgId;


    private String customerName;


    private Integer custSiteId;


    private Integer shipToId;


    private Integer custAcctId;


    private String itemCategories;


    private String itemFamily;


    private String itemClass;


    private Integer ordHeaderId;


    private String orderNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;


    private Integer ordLineId;


    private Integer ordLineNumber;


    private String subInventory;


    private String orderItem;


    private Integer invItemId;


    private Long organizationId;


    private BigDecimal undelvQty;


    private String orderedUom;


    private BigDecimal unitSellingPrice;


    private String deliveryInfo;


    private String orderNumberMode;


    private String modeOfTransport;


    private String modeOfTransName;


    private String workOrderInfo;


    private String dimOrdFlage;


    private String width;


    private String height;


    private Long pcs;


    private BigDecimal conversionCalValue;


    private String undelvPendingSts;


}
