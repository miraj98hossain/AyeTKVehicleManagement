package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Miraj
 * @date: 01/01/2026
 * @time: 14:19
 * @project: AyeTKVehicleManagement
 */
@FeignClient(name = "StepTransTimeStampVFeignClient",
        url = "${backend.service.url}/api/strans-time-stamp")
public interface StepTransTimeStampVFeignClient {
    @GetMapping("/getTimeStampByDetailsId/{stepSetupDetailsId}")
    ResponseEntity<ApiRequestResponse> getTimeStampByDetailsId(@PathVariable Long stepSetupDetailsId);
}
