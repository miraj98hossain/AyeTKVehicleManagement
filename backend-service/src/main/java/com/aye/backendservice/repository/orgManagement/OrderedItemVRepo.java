package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.order.OrderedItemV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:26
 */
public interface OrderedItemVRepo extends JpaRepository<OrderedItemV, Long> {
    List<OrderedItemV> findAllByOrgIdAndInvOrgIdAndOrderedItemLikeIgnoreCase(Long orgId, Long invOrgId, String orderedItem);
}
