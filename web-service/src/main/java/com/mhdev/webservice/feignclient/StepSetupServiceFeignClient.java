package com.mhdev.webservice.feignclient;

import com.mhdev.commonlib.dto.request.StepSetupRequest;
import com.mhdev.commonlib.dto.response.StepSetupDetailsResponse;
import com.mhdev.commonlib.dto.response.StepSetupResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "StepSetupServiceFeignClient", url = "${backend.service.url}${backend.service.steps.setup.prefix}")
public interface StepSetupServiceFeignClient {


    @PostMapping("/save")
    StepSetupResponse saveStepSetup(@RequestBody StepSetupRequest stepSetupRequest);


    @GetMapping("/{id}")
    List<StepSetupDetailsResponse> getStepSetup(@PathVariable("id") Long id);

    @GetMapping()
    List<StepSetupResponse> getAllStepsSetup();
}
