package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportCustCollWiseApp implements Serializable {

    private String appOrdNum;

    private BigDecimal appAmount;


}
