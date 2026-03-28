package com.aye.dtoLib.dto.request.order;

import com.aye.enums.contPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkContractPaymentReqDto {


    private Integer paymentId;
    private Integer bulkContractId;


    private Long contNumber;


    private String payType;


    private String refNumber;


    private String trnsNumber;


    private Long orgId;


    private Long targetOrgId;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trnsDate;


    private String depositBank;


    private Integer depositBankId;


    private contPaymentStatus status;


    private String customerName;


    private String customerAddress;


    private Integer partyId;


    private String accountNumber;


    private Integer custAccId;


    private BigDecimal amount;


}
