package com.aye.vhmwebclient.feignclient;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StepWiseVehicleSummaryVFeignClient",
        url = "${web.service.url}/api/step-trans-dashboard")
public interface StepTransDashBoardFeignClient {


    @GetMapping("/getStepWiseVehicleSummary")
    ResponseEntity<ApiRequestResponse> getStepWiseVehicleSummary();

    @GetMapping("/getStepTransactionVolume")
    ResponseEntity<ApiRequestResponse> getStepTransactionVolume();

    @GetMapping("/getStepTransTotalTime")
    ResponseEntity<ApiRequestResponse> getStepTransTotalTime();

    @GetMapping("/getStrStepWiseTotalTimeByTransId/{stepTransId}")
    ResponseEntity<ApiRequestResponse> getStrStepWiseTotalTimeByTransId(@PathVariable Long stepTransId);
}