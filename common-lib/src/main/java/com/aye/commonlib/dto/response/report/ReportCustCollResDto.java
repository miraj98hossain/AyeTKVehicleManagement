package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportCustCollResDto {
    private BigDecimal orgId;

    private BigDecimal custAcctId;

    private String custName;

    private String custAddress;

    private List<ReportCustDetailCollResDto> reportCustDetailCollResDto;

    private ReportTotalCollResDto reportTotalCollResDto;


}
