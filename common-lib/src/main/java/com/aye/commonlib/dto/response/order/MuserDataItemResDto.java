package com.aye.commonlib.dto.response.order;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter

public class MuserDataItemResDto {


    private Integer id;


    private Long orgId; //


    private Long invOrgId;


    private Integer invItemId;


    private String itemCode;


    private String itemDesc;


    private String itemMask;


    private String uomCode;


    private String status;


    private BigDecimal itemPrice;


    private Integer priceListHeadarId;

    private Integer transactionTypeId;

    private String transactionTypeName;

    private String defaultSubinv;

    private String primarySalesFlag;

    private String primaryUomCode;


    private String itemMaskOrgin;


    private String itemCategories;

    private String itemFamily;
    private String itemClass;


    private String diamentionalItem;


    private BigDecimal calculationValue;


    public interface ItemInfo {
        Integer getOrgId();

        Integer getInvItemId();

        String getItemCode();

        String getItemMask();
    }
}
