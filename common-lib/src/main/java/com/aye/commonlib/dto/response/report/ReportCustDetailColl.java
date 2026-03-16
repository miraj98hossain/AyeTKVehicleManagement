package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportCustDetailColl implements Serializable {

    private String collNum;

    private Date CollectionDate;

    private String bankName;

    private BigDecimal amount;

    private String status;

    private List<ReportCustCollWiseApp> reportCustCollWiseApp;


}
