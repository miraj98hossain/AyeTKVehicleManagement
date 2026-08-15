package com.aye.backendservice.service.orgManagement;


import com.aye.entity.order.OrderedCustomerV;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:02
 */
public interface OrderedCustomerVService {

    List<OrderedCustomerV> filterCustomer(Long orgId, String searchWords);
}
