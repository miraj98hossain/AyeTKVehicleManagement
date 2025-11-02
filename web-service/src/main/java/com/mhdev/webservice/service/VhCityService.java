package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.webservice.feignclient.VhCityServiceFeignClient;
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

