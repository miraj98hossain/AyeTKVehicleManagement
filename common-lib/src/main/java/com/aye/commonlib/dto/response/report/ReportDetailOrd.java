package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailOrd {
 

    private String orderNum;

    private Date orderDate;

    private BigDecimal qty;

    private BigDecimal amount;

    private String status;

    private BigDecimal appAmount;


}
