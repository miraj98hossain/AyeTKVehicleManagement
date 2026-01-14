package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Miraj
 * @date: 13/01/2026
 * @time: 18:08
 */
@FeignClient(name = "ScaleSetupFeignClient",
        url = "${backend.service.url}/api/scale-setup")
public interface ScaleSetupFeignClient {
    @GetMapping
    ResponseEntity<ApiRequestResponse> findAllScaleSetup();

    @GetMapping("/filterScaleSetup")
    ResponseEntity<ApiRequestResponse> filterScaleSetup(@RequestParam Long orgId);
}
