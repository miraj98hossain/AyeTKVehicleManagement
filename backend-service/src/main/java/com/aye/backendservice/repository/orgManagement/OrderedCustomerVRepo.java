package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.order.OrderedCustomerV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:01
 */
public interface OrderedCustomerVRepo extends JpaRepository<OrderedCustomerV, Long> {
    List<OrderedCustomerV> findAllByOrgIdAndPartyNameLikeIgnoreCase(Long orgId, String partyName);
}
