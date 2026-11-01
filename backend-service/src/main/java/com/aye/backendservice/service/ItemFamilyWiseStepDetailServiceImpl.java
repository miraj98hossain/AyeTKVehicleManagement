package com.aye.backendservice.service;

import com.aye.backendservice.repository.ItemFamilyWiseStepDetailRepository;
import com.aye.entity.vehicleproject.ItemFamilyWiseStepDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Miraj
 * @date: 01/11/2026
 * @time: 1:01 pm
 */
@Service
public class ItemFamilyWiseStepDetailServiceImpl implements ItemFamilyWiseStepDetailService {
    @Autowired
    private ItemFamilyWiseStepDetailRepository repository;

    @Transactional(readOnly = true)
    @Override
    public ItemFamilyWiseStepDetail findByOrgIdAndInvOrgId(Long orgId, Long invOrgId, String itemFamily) {
        return this.repository.findByOrgIdAndInvOrgIdAndItemFamily(orgId, invOrgId, itemFamily).orElseThrow(
                () -> new IllegalArgumentException("Item Family Wise Step Detail Not Found! with Org Id" + orgId + " and Inv Org Id" + invOrgId)
        );
    }
}
