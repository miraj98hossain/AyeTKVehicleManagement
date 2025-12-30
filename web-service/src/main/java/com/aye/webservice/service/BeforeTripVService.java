package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.BeforeTripVFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:52
 * @project: AyeTKVehicleManagement
 */


@Service
public class BeforeTripVService {
    @Autowired
    private BeforeTripVFeignClient BeforeTripVFeignClient;

    public ApiRequestResponse getDeliveryNumbers(Long orgId,
                                                 Long invOrgId) {
        return BeforeTripVFeignClient.getDeliveryNumbers(orgId, invOrgId).getBody();
    }
}
