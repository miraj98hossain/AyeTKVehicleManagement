package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.postgresEntity.BeforeTripOrder;
import com.aye.entity.projection.BeforeTripVProjection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 11:22
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripVService {

    List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords);

    @Transactional(readOnly = true)
    List<BeforeTripOrder> findAllByOrderNumber(Long deliveryNo);
}
