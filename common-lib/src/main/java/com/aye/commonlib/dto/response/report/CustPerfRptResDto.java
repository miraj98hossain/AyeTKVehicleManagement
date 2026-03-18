package com.aye.commonlib.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by toufiq on 6/22/2021.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustPerfRptResDto {

    private Integer id;
    private Integer invItemId;
    private String itemMask;
    private String uom;
    private BigDecimal targetQty;
    private BigDecimal dailyTargetQty;
    private BigDecimal saftyStockQty;
    private BigDecimal shortFallqty;
}
