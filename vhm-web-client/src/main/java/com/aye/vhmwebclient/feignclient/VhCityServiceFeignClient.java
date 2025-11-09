package com.aye.vhmwebclient.feignclient;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "VhCityServiceFeignClient"
        , url = "${web.service.url}${web.service.vh.cty.prefix}")
public interface VhCityServiceFeignClient {
    @GetMapping
    ResponseEntity<ApiRequestResponse> getAllVehicleCity();
}
