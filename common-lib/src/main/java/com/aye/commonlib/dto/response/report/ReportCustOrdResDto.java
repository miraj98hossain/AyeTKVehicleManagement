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
public class ReportCustOrdResDto implements Serializable {


    private BigDecimal orgId;
    private BigDecimal custAcctId;
    private String custName;
    private String custAddress;
    private List<ReportDetailOrdResDto> reportDetailOrdResDto;
    private ReportTotalOrdResDto reportTotalOrdResDto;


}
