package com.aye.dtoLib.dto.response.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UndelvOrderListDto {
    Long orgId;

    Long organizationId;

    String subInventory;

    Integer ordHeaderId;

    Long orderNumber;

}