package com.aye.dtoLib.dto.response.order;

import com.aye.enums.RegularData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Munna on 11/20/2023.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkContractSearch {


    private Long orgId;
    private Long invOrgId;
    private Integer custAcctId;
    private String customerName;

    private Integer custSiteId;

    private Integer shipToId;

    private Integer userId;

    private Integer contractId;

    private Long contNumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromrDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date toDate;

    private Integer inventoryItemId;


    private RegularData.ContractStstus status;


}
