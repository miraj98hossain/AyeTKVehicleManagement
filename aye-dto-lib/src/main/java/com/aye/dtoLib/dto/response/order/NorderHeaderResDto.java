package com.aye.dtoLib.dto.response.order;


import com.aye.enums.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NorderHeaderResDto {

    private Integer id;


    private String orderNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    private RegularData.OrderStstus status;


    private Long invOrgId;


    private String subInv;


    private Integer tarnsactionTypeId;


    private Integer billToId;


    private Integer shipToId;


    private String remarks;


    private String workOrderNum;


    private Boolean dimOrderFlage;


    private List<NorderLineResDto> orderLine = new ArrayList<>();


    private Set<OrderCollectionResDto> orderCollection;


    public NorderHeaderResDto(String OrderNumber, Date OrderDate, Long OrgId,
                              Integer CustAcctId, Integer PartyId, String CustomerName, String Address,
                              String UserName, Integer CreatedBy, Date CreationDate,
                              Integer LastUpdateBy, Date LastUpdateDate, BigDecimal TotalAmount, //Double TotalAmount,
                              RegularData.OrderStstus Status,
                              Long InvOrgId, String SubInv,
                              Integer TarnsactionId, String remarks, String workOrderNum, Boolean dimOrderFlage, String OutMsg, List<NorderLineResDto> nl) {
        this.orderNumber = OrderNumber;
        this.orderDate = OrderDate;
        this.orgId = OrgId;
        this.custAcctId = CustAcctId;
        this.partyId = PartyId;
        this.customerName = CustomerName;
        this.customerAddress = Address;
        this.userName = UserName;
        this.createdBy = CreatedBy;
        this.creationDate = CreationDate;
        this.lastUpdateBy = LastUpdateBy;
        this.lastUpdateDate = LastUpdateDate;
        this.totalAmount = TotalAmount;
        this.status = Status;
        this.invOrgId = InvOrgId;
        this.subInv = SubInv;
        this.tarnsactionTypeId = TarnsactionId;
        this.remarks = remarks;
        this.workOrderNum = workOrderNum;
        this.dimOrderFlage = dimOrderFlage;
        this.orderLine = nl;
    }

    public NorderHeaderResDto(NorderHeaderResDto orderHeader) {
        //System.out.println("in The Header");
        this.orderNumber = orderHeader.orderNumber;
        this.orderDate = orderHeader.orderDate;
        this.orgId = orderHeader.orgId;
        this.custAcctId = orderHeader.custAcctId;
        this.partyId = orderHeader.partyId;
        this.customerName = orderHeader.customerName;
        this.customerAddress = orderHeader.customerAddress;
        this.userName = orderHeader.userName;
        this.createdBy = orderHeader.createdBy;
        this.creationDate = orderHeader.creationDate;
        this.lastUpdateBy = orderHeader.lastUpdateBy;
        this.lastUpdateDate = orderHeader.lastUpdateDate;
        this.totalAmount = orderHeader.totalAmount;
        this.status = orderHeader.status;
        this.invOrgId = orderHeader.invOrgId;
        this.subInv = orderHeader.subInv;
        this.tarnsactionTypeId = orderHeader.tarnsactionTypeId;
        this.shipToId = orderHeader.shipToId;
        this.billToId = orderHeader.billToId;
        this.remarks = orderHeader.getRemarks();
        this.workOrderNum = orderHeader.getWorkOrderNum();
        this.dimOrderFlage = orderHeader.dimOrderFlage;
        List<NorderLineResDto> nl = orderHeader.orderLine;
    }


    public interface OrderInf {
        Integer getId();

        String getOrderNumber();

        Date getOrderDate();

        Integer getOrgId();

        Integer getInvOrgId();

        String getCustomerName();

        String getUserName();
    }
}
