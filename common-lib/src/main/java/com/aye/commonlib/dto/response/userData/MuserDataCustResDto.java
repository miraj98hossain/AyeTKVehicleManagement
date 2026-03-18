package com.aye.commonlib.dto.response.userData;

import lombok.Data;

@Data
public class MuserDataCustResDto {

    private Integer id;
    private Long orgId;
    private String autoNumber;
    private Integer partyId;
    private String accountNumber;
    private String custAddress;
    private String shiptoLoc;
    private Integer custAcctId;
    private String customerName;
    private Long setId;
    private Integer custAcctSiteId;
    private Integer billToId;
    private Integer shipToId;
}
