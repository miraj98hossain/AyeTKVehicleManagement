package com.aye.dtoLib.dto.response.order;


import lombok.Data;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:19
 */
@Data
public class OrderedItemVResDto {

    private Long inventoryItemId;
    private String orderedItem;
    private Long orgId;
    private Long invOrgId;
}
