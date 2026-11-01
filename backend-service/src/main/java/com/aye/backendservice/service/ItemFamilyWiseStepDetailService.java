package com.aye.backendservice.service;

import com.aye.entity.vehicleproject.ItemFamilyWiseStepDetail;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Miraj
 * @date: 01/11/2026
 * @time: 1:01 pm
 */

public interface ItemFamilyWiseStepDetailService {
    @Transactional(readOnly = true)
    ItemFamilyWiseStepDetail findByOrgIdAndInvOrgId(Long orgId, Long invOrgId, String itemFamily);
}
