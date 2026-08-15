package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.OrderedCustomerVRepo;
import com.aye.entity.order.OrderedCustomerV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 10:02
 */
@Service
public class OrderedCustomerVServiceImpl implements OrderedCustomerVService {
    @Autowired
    private OrderedCustomerVRepo orderedCustomerVRepo;

    @Override
    public List<OrderedCustomerV> filterCustomer(Long orgId, String searchWords) {
        var list = this.orderedCustomerVRepo.findAllByOrgIdAndPartyNameLikeIgnoreCase(orgId, "%" + searchWords + "%");
        return list;
    }
}
