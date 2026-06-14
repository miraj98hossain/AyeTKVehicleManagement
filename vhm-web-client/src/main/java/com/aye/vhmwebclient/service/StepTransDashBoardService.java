package com.aye.vhmwebclient.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.StepTransDashBoardFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Miraj
 * @date: 06/06/2026
 * @time: 3:11 pm
 */
@Service
public class StepTransDashBoardService {
    @Autowired
    private StepTransDashBoardFeignClient stepTransDashBoardFeignClient;

    public ApiRequestResponse getStepWiseVehicleSummary() {
        return this.stepTransDashBoardFeignClient.getStepWiseVehicleSummary().getBody();
    }


    public ApiRequestResponse getStepTransactionVolume() {
        return this.stepTransDashBoardFeignClient.getStepTransactionVolume().getBody();
    }

    public ApiRequestResponse getStepTransTotalTime() {
        return this.stepTransDashBoardFeignClient.getStepTransTotalTime().getBody();
    }

    public ApiRequestResponse getStrStepWiseTotalTimeByTransId(Long stepTransId) {
        return this.stepTransDashBoardFeignClient.getStrStepWiseTotalTimeByTransId(stepTransId).getBody();
    }
}
