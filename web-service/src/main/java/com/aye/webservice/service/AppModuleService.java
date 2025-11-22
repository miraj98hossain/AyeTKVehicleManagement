package com.aye.webservice.service;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.AppModuleFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class AppModuleService {
    @Autowired
    AppModuleFeignClient appModuleFeignService;

    public ApiRequestResponse findByCode(@RequestParam("appModuleCode") String appModuleCode) {
        return this.appModuleFeignService.findByCode(appModuleCode).getBody();
    }

    public ApiRequestResponse findAll() {
        return this.appModuleFeignService.findAll().getBody();
    }
}
