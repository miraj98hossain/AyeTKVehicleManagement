package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.BeforeTripWDsVFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:52
 * @project: AyeTKVehicleManagement
 */
@Service
public class BeforeTripWDsVService {
    @Autowired
    BeforeTripWDsVFeignClient beforeTripVFeignClient;

    public ApiRequestResponse findScheduleId(Long orgId, Long invOrgId, String searchWords) {
        return this.beforeTripVFeignClient.findScheduleId(orgId, invOrgId, searchWords).getBody();
    }
}
