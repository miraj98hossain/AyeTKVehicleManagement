package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.repository.orgManagement.OrderedItemVRepo;
import com.aye.entity.order.OrderedItemV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 04/01/2026
 * @time: 17:27
 */
@Service
public class OrderedItemVServiceImpl implements OrderedItemVService {
    @Autowired
    private OrderedItemVRepo orderedItemVRepo;

    @Override
    public List<OrderedItemV> filterOrderedItem(Long orgId, Long invOrgId, String searchWords) {
        var list = orderedItemVRepo.findAllByOrgIdAndInvOrgIdAndOrderedItemLikeIgnoreCase(orgId, invOrgId, "%" + searchWords + "%");
        return list;
    }
}
