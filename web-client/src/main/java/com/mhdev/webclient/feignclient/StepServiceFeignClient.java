package com.mhdev.webclient.feignclient;


import com.mhdev.commonlib.dto.request.StepRequest;
import com.mhdev.commonlib.dto.response.StepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "StepServiceFeignClient", url = "${backend.service.url}${backend.service.steps.prefix}")
public interface StepServiceFeignClient {
    @PostMapping("/save")
    StepResponse saveStep(@RequestBody StepRequest stepRequest);

    @PutMapping("/save")
    StepResponse updateStep(@RequestBody StepRequest stepRequest);

    @GetMapping("/{id}")
    StepResponse getStep(@PathVariable("id") Long id);

    @GetMapping()
    Page<StepResponse> getAllSteps(Pageable pageable);

}
