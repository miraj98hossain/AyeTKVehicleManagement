package com.aye.commonlib.dto.response.order;


import com.aye.enums.contractStatus;
import com.aye.enums.mot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulkContractResDto {
    private Integer id;

    private Long orgId;

    private String orgName;

    private Long invOrgId;

    private String invOrgName;

    private Long contNumber;

    private LocalDateTime contractDate;


    private contractStatus status;

    private String customerName;

    private String customerAddress;

    private Integer partyId;

    private String accountNumber;

    private Integer custAcctId;

    private Integer custAcctSiteId;

    private Integer custBillToId;

    private Integer custShipToId;

    private String itemMask;

    private String itemDesc;

    private String itemCode;

    private Long inventoryItemId;

    private String itemTypeCode;

    private String itemTypeName;

    private String contUomName;

    private BigDecimal contUomVal;

    private String soldUomCode;

    private BigDecimal soldUomRate;

    private BigDecimal rate;

    private BigDecimal qty;

    private BigDecimal amount;

    private BigDecimal pricePerUnit;

    private BigDecimal loadingPrice;

    private BigDecimal totalAmount;

    private mot modOfTransport;

    private Integer parentContNumber;

    private String remarks;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;


    private Integer createdBy;


    private Date creationDate;

    private Integer lastUpdateBy;

    private Date lastUpdateDate;

    private Integer tarnsactionTypeId;

    private Integer mapUseDataItemId;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date forwardFDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date forwardTDate;


    private String contractType;

    private String baseUom;

    private String paymentType;

    private String reason;


    private List<BulkContractPaymentResDto> bulkContractPayments = new ArrayList<>();
    
    public interface ContractList {
        Long getOrgId();

        Long getInvOrgId();

        Integer getCreatedBy();

        Integer getId();

        Long getContNumber();

    }
}