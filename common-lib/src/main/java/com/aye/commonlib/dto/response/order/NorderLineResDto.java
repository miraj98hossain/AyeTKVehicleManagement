package com.aye.commonlib.dto.response.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NorderLineResDto {


    private Integer lineId;


    private Long orgId;


    private Integer invItemId;


    private String orderItem;


    private BigDecimal qty;


    private BigDecimal rate;


    private String uomCode;


    private Long invOrgId;


    private String subInv;


    private Integer createdBy;


    private Date creationDate;


    private Integer lastUpdateBy;


    private Date lastUpdateDate;


    private BigDecimal approveQty;


    private BigDecimal approveRate;


    private BigDecimal adjustedAmount;


    private BigDecimal adjustedAmtOrgin;


    private Integer priceListHeadarId;
    private MuserDataItemResDto muserDataItem;


    private Integer muserDataItemId;


    private NorderHeaderResDto norderHeaderResDto;

    private BigDecimal width;


    private BigDecimal height;


    private BigDecimal pcs;


    private String deliveryInfo;


    private BigDecimal conversionCalValue;


    public NorderLineResDto(Long OrgId, Integer InvItemId, String orderItem, BigDecimal Qty, BigDecimal Rate, //Double Qty, Double Rate,
                            String UomCode,
                            Long InvOrgId, String SubInv, Integer CreatedBy, Date CreationDate, Integer LastUpdateBy,
                            Date LastUpdateDate, BigDecimal ApproveQty, BigDecimal ApproveRate, //Double ApproveQty, Double ApproveRate,
                            Integer PriceListHeadarId, BigDecimal Width, BigDecimal Height, BigDecimal Pcs, BigDecimal ConversionCalValue) {
        this.orgId = OrgId;
        this.invItemId = InvItemId;
        this.orderItem = orderItem;
        this.qty = Qty;
        this.rate = Rate;
        this.uomCode = UomCode;
        this.invOrgId = InvOrgId;
        this.subInv = SubInv;
        this.createdBy = CreatedBy;
        this.creationDate = CreationDate;
        this.lastUpdateBy = LastUpdateBy;
        this.lastUpdateDate = LastUpdateDate;
        this.approveQty = ApproveQty;
        this.approveRate = ApproveRate;
        this.priceListHeadarId = PriceListHeadarId;
        this.width = Width;
        this.height = Height;
        this.pcs = Pcs;
        this.conversionCalValue = ConversionCalValue;

    }

    public NorderLineResDto(List<NorderLineResDto> orderLine) {
        for (NorderLineResDto nl : orderLine) {
            this.orgId = nl.orgId;
            this.invItemId = nl.invItemId;
            this.qty = nl.qty;
            this.rate = nl.rate;
            this.uomCode = nl.uomCode;
            this.invOrgId = nl.invOrgId;
            this.subInv = nl.subInv;
            this.createdBy = nl.createdBy;
            this.creationDate = nl.creationDate;
            this.lastUpdateBy = nl.lastUpdateBy;
            this.lastUpdateDate = nl.lastUpdateDate;
            this.approveQty = nl.approveQty;
            this.approveRate = nl.approveRate;
            this.priceListHeadarId = nl.priceListHeadarId;
            this.width = nl.width;
            this.height = nl.height;
            this.pcs = nl.pcs;
            this.conversionCalValue = nl.conversionCalValue;
        }
    }

    public NorderLineResDto(NorderLineResDto orderLine) {
        this.orgId = orderLine.orgId;
        this.invItemId = orderLine.invItemId;
        this.qty = orderLine.qty;
        this.rate = orderLine.rate;
        this.uomCode = orderLine.uomCode;
        this.invOrgId = orderLine.invOrgId;
        this.subInv = orderLine.subInv;
        this.createdBy = orderLine.createdBy;
        this.creationDate = orderLine.creationDate;
        this.lastUpdateBy = orderLine.lastUpdateBy;
        this.lastUpdateDate = orderLine.lastUpdateDate;
        this.approveQty = orderLine.approveQty;
        this.approveRate = orderLine.approveRate;
        this.priceListHeadarId = orderLine.priceListHeadarId;
        this.width = orderLine.width;
        this.height = orderLine.height;
        this.pcs = orderLine.pcs;
        this.conversionCalValue = orderLine.conversionCalValue;
    }
}
