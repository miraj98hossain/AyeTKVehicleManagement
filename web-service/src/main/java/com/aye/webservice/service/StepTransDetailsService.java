package com.aye.webservice.service;

import com.aye.commonlib.dto.request.StepTransDetailsRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.StepTransDetailsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 29/12/2025
 * @time: 13:16
 * @project: AyeTKVehicleManagement
 */
@Service
public class StepTransDetailsService {
    @Autowired
    private StepTransDetailsFeignClient stepTransDetailsFeignClient;

    public ApiRequestResponse create(
            String userName,
            StepTransDetailsRequest stepTransDetailsRequest
    ) {
        return this.stepTransDetailsFeignClient.create(userName, stepTransDetailsRequest).getBody();
    }

    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        return this.stepTransDetailsFeignClient.findAllByStepTransId(stepTransId).getBody();
    }


    public ApiRequestResponse findById(Long id) {
        return this.stepTransDetailsFeignClient.findById(id).getBody();
    }
}
