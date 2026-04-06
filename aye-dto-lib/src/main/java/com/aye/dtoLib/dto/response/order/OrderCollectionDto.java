package com.aye.dtoLib.dto.response.order;

import com.aye.enums.AppFrom;
import com.aye.enums.OrdColStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderCollectionDto {
    private Integer id;


    private NorderHeaderDto order;


    private OnlineCollectionDto collection;


    private Integer createdBy;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SS aa")
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

    public OrderCollectionDto(OrderCollectionDto orCol) {
        this.order = orCol.getOrder();
        this.collection = orCol.collection;
        this.createdBy = orCol.createdBy;
        this.creationDate = orCol.creationDate;
        this.lastUpdateBy = orCol.lastUpdateBy;
        this.lastUpdateDate = orCol.lastUpdateDate;
        this.orderAmount = orCol.orderAmount;
        this.collectionAmount = orCol.collectionAmount;
        this.applyAmount = orCol.applyAmount;
        this.orgId = orCol.orgId;
        this.custAcctId = orCol.custAcctId;
        this.customerName = orCol.customerName;
        this.status = orCol.status;
        this.applyFrom = orCol.applyFrom;

    }

    public OrderCollectionDto(NorderHeaderDto h, OnlineCollectionDto ocl, AppFrom appFrom, Integer userId) {
        this.order = h;
        this.collection = ocl;
        this.createdBy = userId;
        this.creationDate = new Date();
        this.orderAmount = h.getTotalAmount();
        this.collectionAmount = ocl.getAmount();
//		this.applyAmount=new Double(0);
        this.applyAmount = new BigDecimal(0);
        this.orgId = h.getOrgId();
        this.custAcctId = h.getCustAcctId();
        this.customerName = h.getCustomerName();
        this.status = OrdColStatus.P;
        this.applyFrom = appFrom;


    }


    public OrderCollectionDto() {

    }

    public OrderCollectionDto(List<OrderCollectionDto> collection) {


        for (OrderCollectionDto ocl : collection) {
            new OrderCollectionDto(ocl);
        }

    }


}
