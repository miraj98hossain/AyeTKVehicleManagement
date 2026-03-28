package com.aye.dtoLib.dto.response.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderType {

    private Integer orderTypeId;

    private String orderTypeName;

    private Integer invOrganizationId;

    public OrderType(Integer orderTypeID, String orderTypeName, Integer invOrganizationid) {
        this.orderTypeId = orderTypeID;
        this.orderTypeName = orderTypeName;
        this.invOrganizationId = invOrganizationid;
    }

}
