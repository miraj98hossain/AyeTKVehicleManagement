package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 11:45
 */

@Data
public class NOrderLineResponse {

    private Integer lineId;
    private Long orgId;
    private Integer invItemId;
    private String orderItem;
    private BigDecimal qty;
    private BigDecimal rate;
    private String uomCode;
    private Long invOrgId;
    private String subInv;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateDate;
    private BigDecimal approveQty;
    private BigDecimal approveRate;
    private BigDecimal adjustedAmount;
    private BigDecimal adjustedAmtOrgin;
    private Integer priceListHeadarId;
    private Integer muserDataItemId;
    private Integer nOrderHeaderId;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal pcs;
    private String deliveryInfo;
    private BigDecimal conversionCalValue;


}
