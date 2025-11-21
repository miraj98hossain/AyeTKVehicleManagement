package com.aye.commonlib.dto.response;

import lombok.Data;

@Data
public class MuserDataCustResponse {

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
