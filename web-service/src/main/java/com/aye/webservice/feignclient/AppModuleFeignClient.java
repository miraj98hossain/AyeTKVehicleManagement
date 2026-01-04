package com.aye.webservice.feignclient;


import com.aye.commonlib.dto.request.AppModuleRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "AppModuleFeignClient",
        url = "${backend.service.url}/api/app-module")
public interface AppModuleFeignClient {

    @GetMapping("/findByCode")
    ResponseEntity<ApiRequestResponse> findByCode(@RequestParam("appModuleCode") String appModuleCode);

    @GetMapping("/findAll")
    ResponseEntity<ApiRequestResponse> findAll();

    @GetMapping("/findById/{id}")
    ResponseEntity<ApiRequestResponse> findById(@PathVariable("id") Long id);

    @GetMapping("/findByIds")
    ResponseEntity<ApiRequestResponse> findByIds(@RequestBody List<Long> ids);

    @PostMapping("/saveAppmodule")
    ResponseEntity<ApiRequestResponse> saveAppmodule(@Valid @RequestBody AppModuleRequest appModuleRequest);
}
