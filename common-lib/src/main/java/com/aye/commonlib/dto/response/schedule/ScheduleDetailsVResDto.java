package com.aye.commonlib.dto.response.schedule;

import com.aye.commonlib.dto.RegularData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter

public class ScheduleDetailsVResDto implements Serializable {


    private Long id;

    private Integer scheduleHeaderId;


    private Integer createdBy;


    private Date creationDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endtDate;


    private Long orgId;


    private String scheduleNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    private RegularData.ScheduleStstus status;


    private Long invOrgId;


    private String userName;


    private Long scdCustId;


    private Integer custAcctId;


    private String customerName;


    private Integer custSiteId;


    private Integer shipToId;


    private Long scdLineId;


    private Integer invItemId;


    private String orderItem;


    private BigDecimal scdQty;


    private BigDecimal qty;


    private BigDecimal rate;


    private String uomCode;


    private String subInv;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lineStartDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lineEndtDate;


    private Integer ordHeaderId;


    private Integer ordLineId;


    private Long orderNumber;


    private String deliveryInfo;


    private String vehicleInfo;


    private String transportMode;


    private String driverInfo;


    private String workOrderInfo;

    private Integer dimOrdFlage;


    private BigDecimal width;


    private BigDecimal height;


    private BigDecimal pcs;


    private String itemCategories;


    private String itemFamily;


    private String itemClass;


}
