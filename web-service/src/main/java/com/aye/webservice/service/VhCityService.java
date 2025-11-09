package com.aye.webservice.service;

import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.VhCityServiceFeignClient;
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

