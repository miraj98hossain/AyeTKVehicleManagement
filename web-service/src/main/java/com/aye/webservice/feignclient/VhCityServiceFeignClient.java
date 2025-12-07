package com.aye.webservice.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "VhCityServiceFeignClient",
        url = "${backend.service.url}/api/vh-cty")
public interface VhCityServiceFeignClient {
    @GetMapping
    ResponseEntity<ApiRequestResponse> getAllVehicleCity();
}
