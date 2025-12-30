package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 12:52
 * @project: AyeTKVehicleManagement
 */

@FeignClient(name = "BeforeTripVFeignClient",
        url = "${backend.service.url}/api/before-trip-V")
public interface BeforeTripVFeignClient {


    @GetMapping("/getDeliveryNumbers")
    ResponseEntity<ApiRequestResponse> getDeliveryNumbers(@RequestParam Long orgId,
                                                          @RequestParam Long invOrgId);
}
