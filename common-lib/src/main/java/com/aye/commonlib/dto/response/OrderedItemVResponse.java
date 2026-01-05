package com.aye.commonlib.dto.response;


import lombok.Data;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:19
 */
@Data
public class OrderedItemVResponse {

    private Long inventoryItemIdId;
    private String orderedItem;
    private Long orgId;
    private Long invOrgId;
}
