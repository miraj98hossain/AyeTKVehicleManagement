package com.mhdev.webservice.feignclient;

import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "StepSetupServiceFeignClient", url = "${backend.service.url}${backend.service.steps.setup.prefix}")
public interface StepSetupServiceFeignClient {


    @PostMapping("/save")
    ResponseEntity<ApiRequestResponse> saveStepSetup(@RequestBody StepSetupRequest stepSetupRequest);


    @GetMapping("/{id}")
    ResponseEntity<ApiRequestResponse> getStepSetup(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<ApiRequestResponse> getAllStepsSetup(Pageable pageable);
}
