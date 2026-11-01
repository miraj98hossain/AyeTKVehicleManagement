package com.aye.backendservice.service.orgManagement;


import com.aye.backendservice.postgresEntity.BeforeTripSchedule;
import com.aye.entity.projection.BeforeTripWDsVProjection;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:43
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripWDsVService {

    List<BeforeTripWDsVProjection> findScheduleId(Long orgId, Long invOrgId, String searchWords);

    List<BeforeTripSchedule> findAllByScheduleNumber(String scheduleNo);
}
