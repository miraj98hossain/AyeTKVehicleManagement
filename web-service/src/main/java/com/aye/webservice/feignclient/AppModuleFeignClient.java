package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "AppModuleFeignClient",
        url = "${backend.service.url}/api/app-module")
public interface AppModuleFeignClient {

    @GetMapping("/findByCode")
    ResponseEntity<ApiRequestResponse> findByCode(@RequestParam("appModuleCode") String appModuleCode);

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();
}
