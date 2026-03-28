package com.aye.vhmwebclient.service;

import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.vhmwebclient.feignclient.VhCityServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VhCityService {
    @Autowired
    VhCityServiceFeignClient vhCityServiceFeignClient;

    public ApiRequestResponse getAllVehicleCity() {
        return vhCityServiceFeignClient.getAllVehicleCity().getBody();
    }

}

