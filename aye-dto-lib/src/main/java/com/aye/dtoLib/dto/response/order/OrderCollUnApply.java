package com.aye.dtoLib.dto.response.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCollUnApply {


    private Long orgid;


    private BigDecimal orderCollectionId;


    private String orderCollectionNumber;


    private String customerName;


    private Integer custAccountId;


    private Integer partyId;


    private BigDecimal amount;


    private BigDecimal appliedAmount;


    private BigDecimal balanceAmount;

}
