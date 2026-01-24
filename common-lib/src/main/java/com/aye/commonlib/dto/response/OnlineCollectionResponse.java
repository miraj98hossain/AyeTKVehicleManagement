package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Miraj
 * @date: 17/01/2026
 * @time: 11:53
 */
@Data
public class OnlineCollectionResponse {

    private Integer collectionId;
    private Long orgId;

    private Long targetOrgId;

    private Date collectionDate;

    private Integer custAcctId;

    private Integer partyId;

    private String customerName;

    private String customerAddress;

    private String refType;

    private String refNumber;

    private String chqNumber;

    private BigDecimal amount;
    private Date depositeDate;

    private String depositBank;

    private Integer depositBankId;
    private String status;

    private Integer billToId;

    private Integer shipToId;

    private Integer createdBy;

    private Date creationDate;

    private Integer lastUpdateBy;

    private Date lastUpdateDate;

    private String remarks;
    private List<OrderCollectionRefResponse> orderCollection = new ArrayList<>();

}
