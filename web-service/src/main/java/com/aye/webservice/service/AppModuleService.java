package com.aye.webservice.service;


import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.webservice.feignclient.AppModuleFeignClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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


    public ApiRequestResponse findById(@PathVariable("id") Long id) {
        return this.appModuleFeignService.findById(id).getBody();
    }


    public ApiRequestResponse saveAppmodule(@Valid @RequestBody AppModuleRequest appModuleRequest) {
        return this.appModuleFeignService.saveAppmodule(appModuleRequest).getBody();
    }
}
