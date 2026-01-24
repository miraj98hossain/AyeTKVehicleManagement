package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Miraj
 * @date: 15/01/2026
 * @time: 11:40
 */
@Data
public class NOrderHeaderResponse {

    private Integer id;
    private String orderNumber;
    private Date orderDate;
    private Long orgId;
    private Integer custAcctId;
    private Integer partyId;
    private String customerName;
    private String customerAddress;
    private String userName;
    private Integer createdBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdateLogin;
    private Date lastUpdateDate;
    private BigDecimal totalAmount;
    private BigDecimal adjustedAmount;
    private String status;
    private Long invOrgId;
    private String subInv;
    private Integer tarnsactionTypeId;
    private Integer billToId;
    private Integer shipToId;
    private String remarks;
    private String workOrderNum;
    private Boolean dimOrderFlage;
    private List<NOrderLineResponse> orderLine = new ArrayList<>();
//    private Set<OrderCollection> orderCollection;

}
