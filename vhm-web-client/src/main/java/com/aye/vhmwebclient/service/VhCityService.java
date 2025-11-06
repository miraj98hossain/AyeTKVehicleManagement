package com.aye.vhmwebclient.service;

import com.aye.vhmwebclient.feignclient.VhCityServiceFeignClient;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.stereotype.Service;


@Service
public class VhCityService {

    VhCityServiceFeignClient vhCityServiceFeignClient;

    public ApiRequestResponse getAllVehicleCity() {
        return vhCityServiceFeignClient.getAllVehicleCity().getBody();
    }

}

