package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.StepTransTimeStampVFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 14:21
 * @project: AyeTKVehicleManagement
 */
@Service
public class StepTransTimeStampVService {
    @Autowired
    private StepTransTimeStampVFeignClient stepTransTimeStampVFeignClient;
    
    public ApiRequestResponse getTimeStampByDetailsId(Long stepSetupDetailsId) {
        return this.stepTransTimeStampVFeignClient.getTimeStampByDetailsId(stepSetupDetailsId).getBody();
    }
}
