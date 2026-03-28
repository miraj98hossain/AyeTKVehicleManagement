package com.aye.dtoLib.dto.response.report;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter

@NoArgsConstructor
public class ReportCustOrdResDto implements Serializable {


    private BigDecimal orgId;
    private BigDecimal custAcctId;
    private String custName;
    private String custAddress;
    private List<ReportDetailOrdResDto> reportDetailOrdResDto;
    private ReportTotalOrdResDto reportTotalOrdResDto;

    public ReportCustOrdResDto(BigDecimal OrgId, BigDecimal CustAcctId, String CustName, String custAddress,
                               List<ReportDetailOrdResDto> reportDetailOrd, ReportTotalOrdResDto reportTotalOrd) {

        setCustAcctId(CustAcctId);
        setCustName(CustName);
        setCustAddress(custAddress);
        setOrgId(OrgId);
        setReportDetailOrdResDto(reportDetailOrd);
        setReportTotalOrdResDto(reportTotalOrd);
    }
}
