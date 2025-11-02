package com.mhdev.webservice.feignclient;

import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "VhCityServiceFeignClient"
        , url = "${backend.service.url}${backend.service.vh.cty.prefix}")
public interface VhCityServiceFeignClient {
    @GetMapping
    ResponseEntity<ApiRequestResponse> getAllVehicleCity();
}
