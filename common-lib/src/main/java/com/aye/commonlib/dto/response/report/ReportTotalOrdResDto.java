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
public class ReportTotalOrdResDto implements Serializable {


    private BigDecimal totalQty;

    private BigDecimal totalAmount;

    private BigDecimal totalAppAmount;

}
