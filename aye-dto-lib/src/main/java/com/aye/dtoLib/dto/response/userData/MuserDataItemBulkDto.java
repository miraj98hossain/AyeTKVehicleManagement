package com.aye.dtoLib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MuserDataItemBulkDto {

    private Integer id;

    private Long orgId;

    private Long invOrgId;

    private Long invItemId;

    private String itemCode;

    private String itemDesc;

    private String itemMask;

    private String uomCode;

    private String itemTypeCode;

    private String contractUomName;

    private String itemTypeName;

    private String soldUomCode;

    private BigDecimal soldUomRate;

    private BigDecimal contUomVal;

    private String status;

    private BigDecimal itemPrice;

    private Integer priceListHeadarId;

    private Integer transactionTypeId;

    private String transactionTypeName;

    private String baseUom;

}