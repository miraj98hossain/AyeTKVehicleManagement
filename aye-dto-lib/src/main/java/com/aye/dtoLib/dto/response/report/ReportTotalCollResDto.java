package com.aye.dtoLib.dto.response.report;


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
public class ReportTotalCollResDto implements Serializable {


    private BigDecimal totalAmt;

    private BigDecimal totalAppAmount;


}
