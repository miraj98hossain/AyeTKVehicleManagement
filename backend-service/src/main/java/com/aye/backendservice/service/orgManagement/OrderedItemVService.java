package com.aye.backendservice.service.orgManagement;


import com.aye.entity.order.OrderedItemV;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:27
 */
public interface OrderedItemVService {
    List<OrderedItemV> filterOrderedItem(Long orgId, Long invOrgId, String searchWords);
}
