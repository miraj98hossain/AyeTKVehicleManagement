package com.aye.commonlib.dto.response.report;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportHeader implements Serializable {


    private String attr1;

    private String attr2;

    private Date attr3;

    private Date attr4;


    private BigDecimal gQty;

    private BigDecimal gAmount;

    private BigDecimal gAppAmount;


    private List<?> reportMain;


}
