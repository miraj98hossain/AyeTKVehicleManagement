package com.aye.commonlib.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Miraj
 * @date: 17/01/2026
 * @time: 11:22
 */
@Data
public class OrderCollectionResponse {
    private Integer id;

    private NOrderHeaderResponse order;

    private OnlineCollectionResponse collection;

    private Integer createdBy;


    private Date creationDate;

    private Integer lastUpdateBy;

    private Date lastUpdateDate;

    private BigDecimal orderAmount;

    private BigDecimal collectionAmount;

    private BigDecimal applyAmount;

    private Long orgId;

    private Integer custAcctId;

    private String customerName;

    private OrdColStatus status;


    private AppFrom applyFrom;


    public enum AppFrom {
        C, O;
    }

    public enum OrdColStatus {
        N, P;
    }
}
