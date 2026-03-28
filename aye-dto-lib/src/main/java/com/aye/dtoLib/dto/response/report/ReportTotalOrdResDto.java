package com.aye.dtoLib.dto.response.report;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter

@NoArgsConstructor
public class ReportTotalOrdResDto implements Serializable {


    private BigDecimal totalQty;

    private BigDecimal totalAmount;

    private BigDecimal totalAppAmount;

    public ReportTotalOrdResDto(BigDecimal TotalQty,
                                BigDecimal TotalAmount,
                                BigDecimal TotalAppAmount) {

        setTotalAmount(TotalAmount);
        setTotalAppAmount(TotalAppAmount);
        setTotalQty(TotalQty);

    }

}
