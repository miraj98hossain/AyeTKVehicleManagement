package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportCustOrd implements Serializable {


    private BigDecimal orgId;
    private BigDecimal custAcctId;
    private String custName;
    private String custAddress;
    private List<ReportDetailOrd> reportDetailOrd;
    private ReportTotalOrd reportTotalOrd;


}
