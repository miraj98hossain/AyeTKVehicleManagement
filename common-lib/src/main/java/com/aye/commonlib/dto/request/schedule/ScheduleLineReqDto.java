package com.aye.commonlib.dto.request.schedule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLineReqDto {

    private Integer scdLineId;

    private ScheduleHeaderReqDto scheduleHeader;

    private ScheduleCustReqDto scheduleCust;

    private Integer createdBy;


    private Date creationDate;

    private Integer lastUpdateBy;

    private Date lastUpdateDate;


    private Date lastUpdateLogin;

    private Integer invItemId;

    private String orderItem;

    private BigDecimal scdQty;

    private BigDecimal qty;

    private BigDecimal rate;

    private String uomCode;

    private Long invOrgId;

    private Long orgId;

    private String subInv;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date lineStartDate;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
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

    private BigDecimal conversionCalValue;
}
