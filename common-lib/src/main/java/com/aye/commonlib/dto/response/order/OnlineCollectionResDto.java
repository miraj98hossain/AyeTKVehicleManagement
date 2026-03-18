package com.aye.commonlib.dto.response.order;

import com.aye.commonlib.dto.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineCollectionResDto {

    private Integer collectionId;


    private Long orgId;


    private Long targetOrgId;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date collectionDate;


    private Integer custAcctId;


    private Integer partyId;


    private String customerName;


    private String customerAddress;


    private String refType;


    private String refNumber;


    private String chqNumber;

    private BigDecimal amount;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date depositeDate;


    private String depositBank;


    private Integer depositBankId;


    private RegularData.OnlineCollectionStstus status;


    private Integer billToId;


    private Integer shipToId;


    private Integer createdBy;


    private Date creationDate;


    private Integer lastUpdateBy;


    private Date lastUpdateDate;


    private String remarks;

    private List<OrderCollectionResDto> orderCollectionResDto = new ArrayList<>();

    private List<MUploadModelResDto> uploadModels = new ArrayList<>();


    public enum OnlinePayType {
        Online, CHQ
    }

}
